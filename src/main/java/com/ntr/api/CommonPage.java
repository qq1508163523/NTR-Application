package com.ntr.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ntr.util.ObjectUtil;
import lombok.Data;
import lombok.SneakyThrows;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommonPage<T> {

    /* current page number */
    private Long pageNum;

    /* size of each page */
    private Long pageSize;

    /* total pages */
    private Long totalPage;

    /* total records */
    private Long total;

    /* page data */
    private List<T> dataList;


    public static <T> CommonPage<T> restPage(IPage<T> iPage){
        CommonPage<T> restResult = new CommonPage<>();
        restResult.setPageNum(iPage.getCurrent());
        restResult.setPageSize(iPage.getSize());
        restResult.setTotalPage(iPage.getPages());
        restResult.setTotal(iPage.getTotal());
        restResult.setDataList(iPage.getRecords());
        return restResult;
    }

    @SneakyThrows
    public static <T> CommonPage<T> restPage(SearchResponse searchResponse, int from, int pageSize, Class<T> clazz){
        CommonPage<T> restResult = new CommonPage<>();
        restResult.setPageNum((long)from);
        restResult.setPageSize((long)pageSize);
        long totalPages = searchResponse.getHits().getTotalHits().value / pageSize;
        if(searchResponse.getHits().getTotalHits().value % pageSize != 0L){
            totalPages++;
        }
        restResult.setTotalPage(totalPages);
        restResult.setTotal(searchResponse.getHits().getTotalHits().value);
        List<T> list = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            list.add(ObjectUtil.jsonStringToObject(hit.getSourceAsString(),clazz));
        }
        restResult.setDataList(list);
        return restResult;
    }
}
