package com.ntr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ntr.entity.Branch;
import com.ntr.entity.Episode;
import com.ntr.mapper.EpisodeMapper;
import com.ntr.service.BranchService;
import com.ntr.service.EpisodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ntr.util.ResourceUtil.resolveEpisode;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
@Service
public class EpisodeServiceImpl extends ServiceImpl<EpisodeMapper, Episode> implements EpisodeService {

    private final BranchService branchService;

    public EpisodeServiceImpl(BranchService branchService) {
        this.branchService = branchService;
    }

    @Override
    public List<Episode> getEpisodesByBranchId(Integer branchId) {
        LambdaQueryWrapper<Episode> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Episode::getBranchId,branchId);
        return baseMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public Episode getEpisodeByBranchIdAndEpisodeNumber(Integer branchId, String episodeNumber) {
        Branch branch = branchService.getBranchByBranchId(branchId);
        LambdaQueryWrapper<Episode> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Episode::getBranchId,branchId)
                .eq(Episode::getEpisodeNumber,episodeNumber);
        return resolveEpisode(baseMapper.selectOne(lambdaQueryWrapper),branch.getFolderLocation());
    }
}
