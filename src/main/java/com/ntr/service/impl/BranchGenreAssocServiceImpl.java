package com.ntr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ntr.entity.BranchGenreAssoc;
import com.ntr.mapper.BranchGenreAssocMapper;
import com.ntr.service.BranchGenreAssocService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
@Service
public class BranchGenreAssocServiceImpl extends ServiceImpl<BranchGenreAssocMapper, BranchGenreAssoc> implements BranchGenreAssocService {

    @Override
    public List<BranchGenreAssoc> getBranchGenreAssocByBranchId(Integer branchId) {
        LambdaQueryWrapper<BranchGenreAssoc> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(BranchGenreAssoc::getBranchId,branchId);
        return baseMapper.selectList(lambdaQueryWrapper);
    }
}
