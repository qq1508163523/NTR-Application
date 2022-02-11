package com.ntr.service;

import com.ntr.api.CommonPage;
import com.ntr.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
public interface CommentService extends IService<Comment> {

    Comment insertComment(Comment comment);

    CommonPage<Comment> getCommentByBranchId(Integer branchId,Long pageNum);

    CommonPage<Comment> getCommentByParent(Integer parent,Long pageNum);

    CommonPage<Comment> getCommentByUsername(String username,Long pageNum);


}
