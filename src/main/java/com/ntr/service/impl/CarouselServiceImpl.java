package com.ntr.service.impl;

import com.ntr.entity.Carousel;
import com.ntr.mapper.CarouselMapper;
import com.ntr.service.CarouselService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ntr.util.ResourceUtil.resolveCarouselList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {

    @Override
    public List<Carousel> listAll() {
        return resolveCarouselList(baseMapper.selectList(null));
    }
}
