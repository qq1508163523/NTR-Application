package com.ntr.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.ArrayList;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

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
@EqualsAndHashCode(callSuper = false)
@Validated
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    private String username;

    @NotNull
    private Integer branchId;

    @Length(min = 1,max = 256,message = "评论不能为空")
    private String content;

    @NotNull
    private Integer parent;

    private String replyTo;

    @TableField(fill = FieldFill.INSERT)
    private Date postTime;

    private transient Account account;

    private transient Branch branch;

    private transient Integer totalReply;

    private transient List<Comment> childrenList = new ArrayList<>();

}
