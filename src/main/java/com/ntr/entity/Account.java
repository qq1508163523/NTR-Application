package com.ntr.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Validated
public class Account implements Serializable {

    private static final long serialVersionUID = 10086L;

    @TableId(value = "username", type = IdType.INPUT)
    @Pattern(regexp = "[a-z0-9A-Z]{6,20}", message = "账号必须为6~20个字母和数字组合")
    @NotNull(message = "用户名不能为空")
    private String username;

    @Email(message = "email格式不正确")
    private String email;

    @Pattern(regexp = "[a-z0-9A-Z!@#$%^&*()]{6,20}", message = "密码必须为6~20个字母和数字和特殊符号组合")
    @NotNull(message = "密码不能为空")
    private String password;

    private String role;

    private String nickname;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT)
    private Date lastPwModifyTime;

    private Integer locked;

    private Integer deleted;

    private String profilePicName;

    private transient String profilePicUrl;
}
