package com.ntr.service;

import com.ntr.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
public interface AccountService extends IService<Account> {

    Account getAccountByUsername(String username);

    Account getAccountByUsernameForComment(String username);

    String emailVerify(String email);

    String verifyCode(String email,String verificationCode);

    String registerAccount(Account account);

    String updateNickname(String username,String nickname);

    String modifyPassword(String username,String oldPassword,String newPassword);

    Account updateProfileImage(String username,String base64Str);
}
