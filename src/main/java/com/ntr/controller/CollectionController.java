package com.ntr.controller;

import com.ntr.api.CommonPage;
import com.ntr.api.CommonResult;
import com.ntr.entity.Collection;
import com.ntr.service.CollectionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/v1/collection")
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_GOD','ROLE_ADMIN')")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping(value="branchId/{branch_id}")
    public CommonResult<Collection> collectionByUsernameAndBranchId(@PathVariable("branch_id") Integer branchId){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return CommonResult.success(collectionService.getCollectionByUsernameAndBranchId(username,branchId));
    }

    @GetMapping(value="")
    public CommonResult<CommonPage<Collection>> collectionByUsername(@RequestParam("pageNum") Long pageNum){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return CommonResult.success(collectionService.getCollectionByUsername(username,pageNum));
    }

    @PostMapping(value="")
    public CommonResult<String> insertCollection(@RequestParam("branchId") Integer branchId) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return CommonResult.success(collectionService.insertCollection(username,branchId));
    }

    @DeleteMapping(value="")
    public CommonResult<String> deleteByUsernameAndBranchId(@RequestParam("branchId") Integer branchId){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return CommonResult.success(collectionService.deleteCollection(username,branchId));
    }

    @DeleteMapping(value="bulk")
    public CommonResult<String> deleteBulk(@RequestParam("branchIdList[]") List<Integer> branchIdList){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return CommonResult.success(collectionService.deleteBulkCollection(username,branchIdList));
    }
}

