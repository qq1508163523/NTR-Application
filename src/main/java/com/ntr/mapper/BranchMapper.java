package com.ntr.mapper;

import com.ntr.entity.Branch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
public interface BranchMapper extends BaseMapper<Branch> {

    List<Branch> selectDailyRecommend();

    List<Branch> selectRecentUpdate();

    List<Branch> selectByWeekday(@Param("weekday") Integer weekday);

    List<Branch> selectSimilarRecommendByBranchId(@Param("branchId") Integer branchId);

}
