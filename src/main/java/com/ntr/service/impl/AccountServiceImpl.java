package com.ntr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ntr.api.Resource;
import com.ntr.entity.Account;
import com.ntr.exception.CommonException;
import com.ntr.mapper.AccountMapper;
import com.ntr.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ntr.util.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    private final EmailUtil emailUtil;
    private final RedisUtil redisUtil;
    private final PasswordEncoder encoder;

    public AccountServiceImpl(EmailUtil emailUtil, RedisUtil redisUtil) {
        this.emailUtil = emailUtil;
        this.redisUtil = redisUtil;
        encoder = new BCryptPasswordEncoder();
    }

    @Override
    public Account getAccountByUsername(String username) {
        return ResourceUtil.resolveAccount(baseMapper.selectById(username));
    }

    @Override
    public Account getAccountByUsernameForComment(String username) {
        LambdaQueryWrapper<Account> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(Account::getNickname,Account::getRole,Account::getProfilePicName,Account::getUsername)
                .eq(Account::getUsername,username);
        return ResourceUtil.resolveAccount(baseMapper.selectOne(lambdaQueryWrapper));
    }

    @Override
    public String emailVerify(String email) {
        LambdaQueryWrapper<Account> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Account::getEmail,email);
        if(baseMapper.selectOne(lambdaQueryWrapper) != null){
            throw new CommonException("该邮箱已被注册");
        }

        String verificationCode = CommonUtil.generateVerificationCode();
        if(redisUtil.hasKey("NTR:VERIFY_TIMEOUT:"+email)){
            throw new CommonException("请稍后再试");
        }

        if(redisUtil.hasKey("NTR:VERIFY:"+email)){
            redisUtil.setIfPresent("NTR:VERIFY:"+email,verificationCode,900L);
        }else{
            redisUtil.setIfAbsent("NTR:VERIFY:"+email,verificationCode,900L);
        }
        emailUtil.sendVerificationCode(email,verificationCode);
        redisUtil.delete("NTR:REGISTER_SECTION:"+email);
        redisUtil.setIfAbsent("NTR:VERIFY_TIMEOUT:"+email,1,45L);
        return "OK";
    }

    @Override
    public String verifyCode(String email, String verificationCode) {
        if(redisUtil.hasKey("NTR:VERIFY:"+email) && verificationCode.equals(redisUtil.get("NTR:VERIFY:"+email))){
            // delete the verification code after verified
            redisUtil.delete("NTR:VERIFY:"+email);
            // create register section,user has 1200 seconds to fill out username and password
            redisUtil.setIfAbsent("NTR:REGISTER_SESSION:"+email,1,1200L);
            return "OK";
        }else{
            // verification doesn't match, throw an exception
            throw new CommonException("验证码不匹配");
        }
    }

    @Override
    public String registerAccount(Account account) {
        if(redisUtil.hasKey("NTR:REGISTER_SESSION:"+account.getEmail())){
            if(baseMapper.selectById(account.getUsername()) != null){
                throw new CommonException("该账号已被注册");
            }
            account.setPassword(encoder.encode(account.getPassword()));
            account.setRole("USER");
            account.setNickname(account.getUsername());
            account.setLocked(0);
            account.setDeleted(0);
            account.setProfilePicName("default-profile-pic.jpg");
            if(baseMapper.insert(account) > 0){
                redisUtil.delete("NTR:REGISTER_SESSION:"+account.getEmail());
                return "OK";
            }
            else throw new CommonException("SERVER错误");
        }else{
            throw new CommonException("注册会话到期，创建失败");
        }
    }

    @Override
    public String updateNickname(String username, String nickname) {
        Account account = new Account();
        account.setUsername(username);
        account.setNickname(nickname);
        if(baseMapper.updateById(account) <= 0){
            throw new CommonException("SERVER错误");
        }
        return "OK";
    }

    @Override
    public String modifyPassword(String username, String oldPassword, String newPassword) {
        Account account = baseMapper.selectById(username);
        if(!encoder.matches(oldPassword,account.getPassword())){
            throw new CommonException("旧密码错误,更改密码失败");
        }
        account.setPassword(encoder.encode(newPassword));
        if(baseMapper.updateById(account) <= 0){
            throw new CommonException("SERVER错误");
        }
        return "OK";
    }

    @Override
    public Account updateProfileImage(String username, String base64Str) {
        String imageName = UUID.randomUUID().toString();
        int commaOffset = base64Str.indexOf(",");
        String imageType = base64Str.substring(base64Str.indexOf("/") + 1,base64Str.indexOf(";"));
        String imageData = base64Str.substring(commaOffset + 1);
        Account account = new Account();
        account.setUsername(username);
        account.setProfilePicName(imageName+"."+imageType);
        String path = Resource.IMG_PATH.getRoot()+"userProfiles\\"+imageName+"."+imageType;
        IOUtil.createImageFromBase64(imageData,path);
        if(this.baseMapper.updateById(account) <= 0){
            throw new CommonException("SERVER错误");
        }
        return this.getAccountByUsername(username);
    }
}
