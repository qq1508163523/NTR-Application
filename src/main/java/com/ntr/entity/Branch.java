package com.ntr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Branch implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "branch_id", type = IdType.AUTO)
    private Integer branchId;

    private Integer seriesId;

    private String translatedName;

    private String originalName;

    private String intro;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd")
    private Date releaseDate;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String status;

    private String imgName;

    private String folderLocation;

    private Integer locked;

    @Version
    private Integer version;

    private String region;

    private transient String imgUrl;
    private transient List<BranchGenreAssoc> genreList;
    private transient List<BranchNickname> nicknameList;
}
