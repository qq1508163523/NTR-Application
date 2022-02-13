package com.ntr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ntr.api.CommonPage;
import com.ntr.entity.Comment;
import com.ntr.exception.CommonException;
import com.ntr.mapper.CommentMapper;
import com.ntr.service.AccountService;
import com.ntr.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ntr.util.ResourceUtil;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    private final AccountService accountService;

    public CommentServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Comment insertComment(Comment comment) {
        if(baseMapper.insert(comment) <= 0){
            throw new CommonException("SERVER ERROR");
        }
        comment = baseMapper.selectById(comment.getCommentId());
        comment.setAccount(accountService.getAccountByUsernameForComment(comment.getUsername()));
        return comment;
    }

    @Override
    public CommonPage<Comment> getCommentByParent(Integer parent, Long pageNum) {
        IPage<Comment> iPage = this.baseMapper.selectCommentByParent(new Page<>(pageNum, 10), parent);
        return this.getComment(iPage);
    }

    @Override
    public CommonPage<Comment> getCommentByBranchId(Integer branchId, Long pageNum) {
        IPage<Comment> iPage = this.baseMapper.selectCommentByBranchId(new Page<>(pageNum, 10), branchId);
        return this.getComment(iPage);
    }

    @Override
    public CommonPage<Comment> getCommentByUsername(String username,Long pageNum) {
        IPage<Comment> iPage = this.baseMapper.selectCommentByUsername(new Page<>(pageNum, 10), username);
        return this.getComment(iPage);
    }

    private CommonPage<Comment> getComment(IPage<Comment> iPage){
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        iPage.getRecords().forEach((x) ->{
            x.setAccount(ResourceUtil.resolveAccount(x.getAccount()));
            lambdaQueryWrapper.clear();
            lambdaQueryWrapper.eq(Comment::getParent,x.getCommentId());
            x.setTotalReply(baseMapper.selectCount(lambdaQueryWrapper));
        });
        return CommonPage.restPage(iPage);
    }
}
