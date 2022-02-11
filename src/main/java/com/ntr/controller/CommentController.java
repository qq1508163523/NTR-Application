package com.ntr.controller;


import com.ntr.api.CommonPage;
import com.ntr.api.CommonResult;
import com.ntr.entity.Comment;
import com.ntr.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("branchId/{branchId}/{pageNum}")
    @ApiOperation("get comments by branch id")
    public CommonResult<CommonPage<Comment>> selectCommentByBranchId(@PathVariable("branchId") Integer branchId,
                                                                     @PathVariable("pageNum") Long pageNum){
        return CommonResult.success(commentService.getCommentByBranchId(branchId,pageNum));
    }

    @GetMapping("username/{pageNum}")
    @ApiOperation("Comment GET API, get comments by username")
    public CommonResult<CommonPage<Comment>> selectCommentByUsername(@PathVariable("pageNum") Long pageNum){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return CommonResult.success(commentService.getCommentByUsername(username,pageNum));
    }

    @GetMapping("parent/{parent}/{pageNum}")
    @ApiOperation("Comment GET API, get comments by branch id")
    public CommonResult<CommonPage<Comment>> selectCommentByUsername(@PathVariable("parent") Integer parent,
                                                                     @PathVariable("pageNum") Long pageNum){
        return CommonResult.success(commentService.getCommentByParent(parent,pageNum));
    }

    @PostMapping("")
    @ApiOperation("Comment insert API, authentication needed")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public CommonResult<Comment> insertComment(@RequestBody @Valid Comment comment){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setUsername(username);
        return CommonResult.success(commentService.insertComment(comment));
    }
}

