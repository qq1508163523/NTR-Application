package com.ntr.service;

import com.ntr.api.CommonPage;
import com.ntr.entity.Collection;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
public interface CollectionService extends IService<Collection> {

    Collection getCollectionByUsernameAndBranchId(String username,Integer branchId);

    CommonPage<Collection> getCollectionByUsername(String username, Long current);

    String insertCollection(String username,Integer branchId);

    String deleteCollection(String username,Integer branchId);

    @Transactional
    String deleteBulkCollection(String username, List<Integer> branchIdList);
}
