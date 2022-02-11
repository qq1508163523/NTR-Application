package com.ntr.controller;


import com.ntr.api.CommonResult;
import com.ntr.entity.Episode;
import com.ntr.service.EpisodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
@RestController
@RequestMapping("/api/v1/episode")
public class EpisodeController {

    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping(value="{branch_id}",produces = {"application/json"})
    public CommonResult<List<Episode>> episodesByBranchId(@PathVariable("branch_id") Integer branchId){
        return CommonResult.success(episodeService.getEpisodesByBranchId(branchId));
    }

    @GetMapping(value="{branch_id}/{episode_number}",produces = {"application/json"})
    public CommonResult<Episode> episodeByBranchIdAndEpisodeNumber(@PathVariable("branch_id") Integer branchId,
                                                                   @PathVariable("episode_number") String episodeNumber){
        return CommonResult.success(episodeService.getEpisodeByBranchIdAndEpisodeNumber(branchId,episodeNumber));
    }

}

