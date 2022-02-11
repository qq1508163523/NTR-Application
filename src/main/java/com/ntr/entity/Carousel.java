package com.ntr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class Carousel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "branch_id", type = IdType.INPUT)
    private Integer branchId;

    private String translatedName;

    private Integer position;

    private String imgName;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private transient String imgUrl;


}
