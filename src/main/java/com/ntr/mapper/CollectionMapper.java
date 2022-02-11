package com.ntr.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ntr.entity.Collection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
public interface CollectionMapper extends BaseMapper<Collection> {

    IPage<Collection> selectCollectionByUsername(IPage<?> iPage,String username);

}
