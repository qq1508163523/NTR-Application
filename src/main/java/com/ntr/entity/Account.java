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
    @Pattern(regexp = "[a-z0-9A-Z]{6,20}", message = "The length of username must be between 6 and 20")
    private String username;

    @Email(message = "Email format invalid")
    private String email;

    @Pattern(regexp = "[a-z0-9A-Z!@#$%^&*()]{6,20}", message = "The length of password must be between 6 and 20")
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
