package com.ntr.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ntr.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
public interface CommentMapper extends BaseMapper<Comment> {

    IPage<Comment> selectCommentByBranchId(IPage<?> iPage,Integer branchId);
    IPage<Comment> selectCommentByParent(IPage<?> iPage,Integer parent);
    IPage<Comment> selectCommentByUsername(IPage<?> iPage,String username);
}
