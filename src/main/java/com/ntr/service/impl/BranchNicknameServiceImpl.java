package com.ntr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ntr.entity.BranchNickname;
import com.ntr.mapper.BranchNicknameMapper;
import com.ntr.service.BranchNicknameService;
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
public class BranchNicknameServiceImpl extends ServiceImpl<BranchNicknameMapper, BranchNickname> implements BranchNicknameService {

    @Override
    public List<BranchNickname> getBranchNicknameByBranchId(Integer branchId) {
        LambdaQueryWrapper<BranchNickname> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(BranchNickname::getBranchId,branchId);
        return baseMapper.selectList(lambdaQueryWrapper);
    }
}
