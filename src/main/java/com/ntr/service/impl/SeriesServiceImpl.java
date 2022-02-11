package com.ntr.service.impl;

import com.ntr.entity.Series;
import com.ntr.mapper.SeriesMapper;
import com.ntr.service.SeriesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
@Service
public class SeriesServiceImpl extends ServiceImpl<SeriesMapper, Series> implements SeriesService {

}
