package com.ntr.service;

import com.ntr.entity.Episode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
public interface EpisodeService extends IService<Episode> {

    List<Episode> getEpisodesByBranchId(Integer branchId);

    Episode getEpisodeByBranchIdAndEpisodeNumber(Integer branchId,String episodeNumber);
}
