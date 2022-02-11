package com.ntr.service;

import com.ntr.entity.BranchGenreAssoc;
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
public interface BranchGenreAssocService extends IService<BranchGenreAssoc> {

    List<BranchGenreAssoc> getBranchGenreAssocByBranchId(Integer branchId);
}
