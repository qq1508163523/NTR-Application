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
public class Episode implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "episode_number", type = IdType.INPUT)
    private String episodeNumber;

    private Integer branchId;

    private String md5;

    private String episodeTitle;

    private String fileType;

    @TableField(fill = FieldFill.INSERT)
    private Date uploadTime;

    private transient String videoUrl;

}
