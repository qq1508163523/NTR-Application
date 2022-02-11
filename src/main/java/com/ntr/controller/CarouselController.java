package com.ntr.controller;


import com.ntr.entity.Carousel;
import com.ntr.service.CarouselService;
import com.ntr.api.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/v1/carousel")
public class CarouselController {

    private final CarouselService carouselService;

    public CarouselController(CarouselService carouselService) {
        this.carouselService = carouselService;
    }

    /**
     * @return all carousels data
     */
    @GetMapping(value = "",produces = {"application/json"})
    @ApiOperation("Get all carousels data")
    public CommonResult<List<Carousel>> allCarousels(){
        return CommonResult.success(carouselService.listAll());
    }

}

