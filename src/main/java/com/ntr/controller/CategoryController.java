package com.ntr.controller;

import com.ntr.api.CommonResult;
import com.ntr.entity.Category;
import com.ntr.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "",produces = {"application/json"})
    @ApiOperation(value = "Get category")
    public CommonResult<Category> category(){
        return CommonResult.success(categoryService.getCategory());
    }
}
