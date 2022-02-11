package com.ntr.controller;


import com.ntr.api.CommonPage;
import com.ntr.api.CommonResult;
import com.ntr.entity.Branch;
import com.ntr.service.BranchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
@RestController
@RequestMapping("/api/v1/search")
public class SearchController {
    private final BranchService branchService;


    public SearchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping(value = "keyword",produces = {"application/json"})
    @ApiOperation("search by keyword API")
    public CommonResult<CommonPage<Branch>> searchByKeyword(@RequestParam() String keyword,
                                                   @RequestParam(required = false) String sortBy,
                                                   @RequestParam(required = false) String orderBy,
                                                   @RequestParam(required = false,defaultValue = "1") Integer from){
        return CommonResult.success(branchService.getBranchesByKeyword(keyword,sortBy,orderBy,--from));
    }

    @GetMapping(value = "categories",produces = {"application/json"})
    public CommonResult<CommonPage<Branch>> searchByCategories(@RequestParam(required = false) Integer year,
                                                               @RequestParam(required = false) Integer month,
                                                               @RequestParam(required = false) String status,
                                                               @RequestParam(required = false) String genre,
                                                               @RequestParam(required = false) String sortBy,
                                                               @RequestParam(required = false) String orderBy,
                                                               @RequestParam(required = false,defaultValue = "1") Integer from){
        return CommonResult.success(branchService.getBranchesByCategories(year,month,status,genre,sortBy,orderBy,--from));
    }
}
