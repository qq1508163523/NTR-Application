package com.ntr.controller;


import com.ntr.api.CommonResult;
import com.ntr.entity.Branch;
import com.ntr.entity.Weekday;
import com.ntr.service.BranchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/v1/branch")
public class BranchController {

    private final BranchService branchService;


    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping(value = "daily_recommend")
    @ApiOperation(value = "Get daily Recommend Branches")
    public CommonResult<List<Branch>> dailyRecommend(){
        return CommonResult.success(branchService.getDailyRecommend());
    }

    @GetMapping(value = "recent_update")
    @ApiOperation(value = "Get recent update Branches")
    public CommonResult<List<Branch>> recentUpdate(){
        return CommonResult.success(branchService.getRecentUpdate());
    }

    @GetMapping(value = "week_schedule")
    @ApiOperation(value = "Get week schedules")
    public CommonResult<List<Weekday>> weekSchedule(){
        return CommonResult.success(branchService.getWeekSchedule());
    }

    @GetMapping(value = "{branchId}/relate")
    @ApiOperation(value = "Get recent update Branches")
    public CommonResult<List<Branch>> relateByBranchId(@PathVariable("branchId") Integer branchId){
        return CommonResult.success(branchService.getRelateByBranchId(branchId));
    }

    @GetMapping(value = "{branchId}")
    @ApiOperation(value = "Get a branch by branch Id")
    public CommonResult<Branch> branchByBranchId(@PathVariable("branchId") Integer branchId){
        return CommonResult.success(branchService.getBranchByBranchId(branchId));
    }

    @GetMapping(value = "{branchId}/similar_recommend")
    @ApiOperation(value = "Get similar branches by branch Id")
    public CommonResult<List<Branch>> similarRecommendByBranchId(@PathVariable("branchId") Integer branchId){
        return CommonResult.success(branchService.getSimilarRecommendByBranchId(branchId));
    }
}

