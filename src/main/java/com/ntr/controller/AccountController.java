package com.ntr.controller;


import com.ntr.api.CommonResult;
import com.ntr.entity.Account;
import com.ntr.service.AccountService;
import com.ntr.util.IOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
@RestController
@RequestMapping("/api/v1/account")
@Validated
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value="")
    @ApiOperation("Get account information")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_GOD','ROLE_ADMIN')")
    public CommonResult<Account> accountByUsername(){
        String username = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return CommonResult.success(accountService.getAccountByUsername(username));
    }

    @PostMapping(value="code/{email}")
    @ApiOperation("Send verification code")
    public CommonResult<String> sendVerificationCode(@PathVariable("email") @Email(message = "Email format invalid") String email){
        return CommonResult.success(accountService.emailVerify(email));
    }

    @PostMapping(value="verify")
    @ApiOperation("Verify the code")
    public CommonResult<String> verifyCode(@RequestParam("email") @Email(message = "Email format invalid") String email,
                                           @RequestParam("verificationCode") String verificationCode){
        return CommonResult.success(accountService.verifyCode(email,verificationCode));
    }

    @PostMapping(value="")
    @ApiOperation("insert new account")
    public CommonResult<String> registerAccount(@RequestBody @Valid Account account){
        return CommonResult.success(accountService.registerAccount(account));
    }

    @PutMapping(value="nickname/{nickname}")
    @ApiOperation("update nickname")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_GOD','ROLE_ADMIN')")
    public CommonResult<String> updateNickname(@PathVariable("nickname") @NotEmpty String nickname){
        String username = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return CommonResult.success(accountService.updateNickname(username,nickname));
    }

    @PutMapping(value="password")
    @ApiOperation("update password")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_GOD','ROLE_ADMIN')")
    public CommonResult<String> modifyPassword(@RequestParam("oldPassword")
                                               @Pattern(regexp = "[a-z0-9A-Z!@#$%^&*()]{6,20}", message = "密码必须为6~20个字母和数字和特殊符号组合")
                                               String oldPassword,
                                               @RequestParam("newPassword")
                                               @Pattern(regexp = "[a-z0-9A-Z!@#$%^&*()]{6,20}", message = "密码必须为6~20个字母和数字和特殊符号组合")
                                               String newPassword){
        String username = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return CommonResult.success(accountService.modifyPassword(username,oldPassword,newPassword));
    }

    @PutMapping(value="image")
    @ApiOperation("update profile image")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_GOD','ROLE_ADMIN')")
    public CommonResult<Account> updateImage(@RequestParam("base64Str") String base64Str){
        String username = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return CommonResult.success(accountService.updateProfileImage(username,base64Str));
    }
}

