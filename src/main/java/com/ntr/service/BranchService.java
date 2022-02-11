package com.ntr.service;

import com.ntr.api.CommonPage;
import com.ntr.entity.Branch;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ntr.entity.Weekday;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
public interface BranchService extends IService<Branch> {


    List<Branch> getDailyRecommend();

    List<Branch> getRecentUpdate();

    List<Weekday> getWeekSchedule();

    List<Branch> getRelateByBranchId(Integer branchId);

    List<Branch> getSimilarRecommendByBranchId(Integer branchId);

    CommonPage<Branch> getBranchesByKeyword(String keyword,String sortBy,String order,Integer from);

    CommonPage<Branch> getBranchesByCategories(Integer year, Integer month,String status,
                                               String genre,String sortBy, String order, Integer from);

    Branch getBranchByBranchId(Integer branchId);


}
