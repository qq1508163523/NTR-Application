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
public class Collection implements Serializable {

    private static final long serialVersionUID = 1L;

    public Collection(String username,Integer branchId){
        this.username = username;
        this.branchId = branchId;
    }

    @TableId(value = "username", type = IdType.INPUT)
    private String username;

    private Integer branchId;

    @TableField(fill = FieldFill.INSERT)
    private Date collectTime;

    private transient Branch branch;

}
