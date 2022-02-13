package com.ntr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ntr.api.CommonPage;
import com.ntr.entity.Collection;
import com.ntr.exception.CommonException;
import com.ntr.mapper.CollectionMapper;
import com.ntr.service.CollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ntr.util.ResourceUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {

    @Override
    public Collection getCollectionByUsernameAndBranchId(String username, Integer branchId) {
        LambdaQueryWrapper<Collection> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Collection::getUsername,username).eq(Collection::getBranchId,branchId);
        return baseMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public CommonPage<Collection> getCollectionByUsername(String username, Long current) {
        IPage<Collection> iPage = this.baseMapper.selectCollectionByUsername(new Page<>(current, 12L), username);
        if(iPage.getPages() < current){
            iPage = this.baseMapper.selectCollectionByUsername(new Page<>(iPage.getPages(),12),username);
        }
        iPage.getRecords().forEach( x -> x.setBranch(ResourceUtil.resolveBranch(x.getBranch())));
        return CommonPage.restPage(iPage);
    }

    @Override
    public String insertCollection(String username,Integer branchId) {
        if(baseMapper.insert(new Collection(username,branchId)) <= 0){
            throw new CommonException("SERVER ERROR");
        }
        return "OK";
    }

    @Override
    public String deleteCollection(String username, Integer branchId) {
        LambdaQueryWrapper<Collection> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Collection::getUsername,username).eq(Collection::getBranchId,branchId);
        if(baseMapper.delete(lambdaQueryWrapper) <= 0){
            throw new CommonException("SERVER ERROR");
        }
        return "OK";
    }

    @Override
    public String deleteBulkCollection(String username, List<Integer> branchIdList) {
        Map<String,Object> map = new HashMap<>();
        branchIdList.forEach(x -> {
            map.put("username",username);
            map.put("branch_id",x);
            if(this.baseMapper.deleteByMap(map) <= 0){
                throw new CommonException("SERVER ERROR");
            }
        });
        return "OK";
    }
}
