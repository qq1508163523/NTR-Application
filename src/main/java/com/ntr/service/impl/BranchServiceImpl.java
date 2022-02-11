package com.ntr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ntr.api.CommonPage;
import com.ntr.api.Index;
import com.ntr.api.WeekReference;
import com.ntr.entity.Branch;
import com.ntr.entity.Weekday;
import com.ntr.mapper.BranchMapper;
import com.ntr.service.BranchGenreAssocService;
import com.ntr.service.BranchNicknameService;
import com.ntr.service.BranchService;
import com.ntr.util.CommonUtil;
import lombok.SneakyThrows;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ntr.util.ResourceUtil.resolveBranch;
import static com.ntr.util.ResourceUtil.resolveBranchList;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Vincent Li
 * @since 2022-01-10
 */
@Service
public class BranchServiceImpl extends ServiceImpl<BranchMapper, Branch> implements BranchService {

    private final BranchNicknameService branchNicknameService;
    private final BranchGenreAssocService branchGenreAssocService;
    private final RestHighLevelClient client;

    public BranchServiceImpl(BranchNicknameService branchNicknameService,
                             BranchGenreAssocService branchGenreAssocService,
                             @Qualifier("restHighLevelClient") RestHighLevelClient client) {
        this.branchNicknameService = branchNicknameService;
        this.branchGenreAssocService = branchGenreAssocService;
        this.client = client;
    }

    @Override
    public List<Branch> getDailyRecommend() {
        return resolveBranchList(baseMapper.selectDailyRecommend());
    }

    @Override
    public List<Branch> getRecentUpdate() {
        return resolveBranchList(baseMapper.selectRecentUpdate());
    }

    @Override
    public List<Weekday> getWeekSchedule() {
        List<Weekday> lists = new ArrayList<>();
        lists.add(new Weekday(WeekReference.MON.getName(),WeekReference.MON.getRefName(),
                resolveBranchList(baseMapper.selectByWeekday(WeekReference.MON.getCode()))));
        lists.add(new Weekday(WeekReference.TUE.getName(),WeekReference.TUE.getRefName(),
                resolveBranchList(baseMapper.selectByWeekday(WeekReference.TUE.getCode()))));
        lists.add(new Weekday(WeekReference.WED.getName(),WeekReference.WED.getRefName(),
                resolveBranchList(baseMapper.selectByWeekday(WeekReference.WED.getCode()))));
        lists.add(new Weekday(WeekReference.THU.getName(),WeekReference.THU.getRefName(),
                resolveBranchList(baseMapper.selectByWeekday(WeekReference.THU.getCode()))));
        lists.add(new Weekday(WeekReference.FRI.getName(),WeekReference.FRI.getRefName(),
                resolveBranchList(baseMapper.selectByWeekday(WeekReference.FRI.getCode()))));
        lists.add(new Weekday(WeekReference.SAT.getName(),WeekReference.SAT.getRefName(),
                resolveBranchList(baseMapper.selectByWeekday(WeekReference.SAT.getCode()))));
        lists.add(new Weekday(WeekReference.SUN.getName(),WeekReference.SUN.getRefName(),
                resolveBranchList(baseMapper.selectByWeekday(WeekReference.SUN.getCode()))));
        return lists;
    }

    @Override
    public List<Branch> getRelateByBranchId(Integer branchId) {
        Branch branch = baseMapper.selectById(branchId);
        LambdaQueryWrapper<Branch> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Branch::getSeriesId,branch
                .getSeriesId()).ne(Branch::getBranchId,branchId);
        return resolveBranchList(baseMapper.selectList(lambdaQueryWrapper));
    }

    @Override
    public List<Branch> getSimilarRecommendByBranchId(Integer branchId) {
        List<Branch> list = baseMapper.selectSimilarRecommendByBranchId(branchId);
        list.forEach(x -> x.setGenreList(branchGenreAssocService.getBranchGenreAssocByBranchId(x.getBranchId())));
        return resolveBranchList(list);
    }

    @Override
    @SneakyThrows({IOException.class})
    public CommonPage<Branch> getBranchesByKeyword(String keyword,String sortBy,String orderBy,Integer from) {
        SearchRequest searchRequest = new SearchRequest(Index.BRANCH.getIndexName());
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        sourceBuilder.query(QueryBuilders.multiMatchQuery(keyword, "translatedName", "originalName", "nicknameList.nickname"));
        sourceBuilder.query(QueryBuilders.termsQuery("translatedName",keyword.split("[\\s-,]")));
        Optional.ofNullable(sortBy).ifPresent(x ->sourceBuilder.sort(x,"DESC".equalsIgnoreCase(orderBy)?SortOrder.DESC:SortOrder.ASC));
        sourceBuilder.from(from * 24).size(24);
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        return CommonPage.restPage(searchResponse,from + 1,24,Branch.class);
    }

    @Override
    @SneakyThrows({IOException.class})
    public CommonPage<Branch> getBranchesByCategories(Integer year, Integer month,String status,
                                                      String genre,String sortBy, String orderBy, Integer from) {
        SearchRequest searchRequest = new SearchRequest(Index.BRANCH.getIndexName());
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //genre
        Optional.ofNullable(genre).ifPresent(x -> boolQueryBuilder.must(QueryBuilders.termsQuery("genreList.genreName",x)));
        //status
        Optional.ofNullable(status).ifPresent(x -> boolQueryBuilder.must(QueryBuilders.termsQuery("status",x)));
        //year and month
        String script = CommonUtil.generateDateScript(year,month,"releaseDate");
        Optional.ofNullable(script).ifPresent( x -> boolQueryBuilder.filter(QueryBuilders.scriptQuery(Script.parse(x))));
        //sort
        Optional.ofNullable(sortBy).ifPresent(x -> sourceBuilder.sort(x,"DESC".equalsIgnoreCase(orderBy)?SortOrder.DESC:SortOrder.ASC));

        sourceBuilder.from(from * 24).size(24);
        sourceBuilder.query(boolQueryBuilder);
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        return CommonPage.restPage(searchResponse,from + 1,24,Branch.class);
    }

    @Override
    public Branch getBranchByBranchId(Integer branchId) {
        Branch branch = baseMapper.selectById(branchId);
        branch.setNicknameList(branchNicknameService.getBranchNicknameByBranchId(branchId));
        branch.setGenreList(branchGenreAssocService.getBranchGenreAssocByBranchId(branchId));
        return resolveBranch(branch);
    }
}
