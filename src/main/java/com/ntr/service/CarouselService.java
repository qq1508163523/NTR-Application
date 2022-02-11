package com.ntr.service;

import com.ntr.entity.Carousel;
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
public interface CarouselService extends IService<Carousel> {

    List<Carousel> listAll();
}
