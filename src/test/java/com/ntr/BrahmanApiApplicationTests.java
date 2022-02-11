package com.ntr;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntr.api.CommonPage;
import com.ntr.api.Index;
import com.ntr.entity.*;
import com.ntr.service.AccountService;
import com.ntr.service.BranchService;
import com.ntr.service.CommentService;
import com.ntr.service.impl.CollectionServiceImpl;
import com.ntr.service.impl.CommentServiceImpl;
import com.ntr.util.*;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
class BrahmanApiApplicationTests {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Autowired
    BranchService branchService;

    @Autowired
    EsUtil<Branch> esUtil;

    @Autowired
    EmailUtil emailUtil;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    AccountService accountService;

    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    CollectionServiceImpl collectionService;

    @Test
    void contextLoads(){
//        List<Branch> list = new ArrayList<>();
//        List<String> ids = new ArrayList<>();
//        for(int i = 1;i <= 112;i++){
//            list.add(branchService.getBranchByBranchId(i));
//            ids.add(i+"");
//        }
//        boolean b = esUtil.insertBulkDoc(Index.BRANCH.getIndexName(), ids, list);
//        if(b){
//            System.out.println("OK");
//        }else{
//            System.out.println("FAIL");
//        }
//        branchService.getBranchesByKeyword("勇者 路人",)
//        CommonPage<Branch> result = branchService.getBranchesByKeyword("路人 会长 beatless", 0, null, null);
//        result.getList().forEach(System.out::println);

//        CategoryQuery categoryQuery = new CategoryQuery(null,null,0,null,null,null,null);
//        CommonPage<Branch> result = branchService.getBranchesByCategories(categoryQuery);
//        result.getList().forEach(System.out::println);
//        System.out.println(new BCryptPasswordEncoder().encode("123456"));
//        IPage<Collection> testaccount = collectionService.getBaseMapper().getCollectionByUsername(new Page<>(1, 5), "testaccount");
//        testaccount.getRecords().forEach(System.out::println);
//        CommonPage<Collection> testaccount = collectionService.getCollectionByUsername("testaccount", 3L);
//        System.out.println(testaccount.getDataList());
    }

    @Test
    void testCreateIndex() throws IOException {
        // create index create request
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("new_index");

        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    @Test
    void testIndexExist() throws IOException {
        GetIndexRequest request = new GetIndexRequest("new_index");
        boolean exist = client.indices().exists(request,RequestOptions.DEFAULT);
        System.out.println(exist);
    }

    @Test
    void testIndexDelete() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("new_index");
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete);
    }

    @Test
    void testInsertDocument() throws IOException {
        Series series = new Series(1,"hello",new Date());
        IndexRequest indexRequest = new IndexRequest("new_index");

        indexRequest.id("1");
        indexRequest.timeout(TimeValue.timeValueSeconds(2));

        ObjectMapper objectMapper = new ObjectMapper();
        //send data
        indexRequest.source(objectMapper.writeValueAsString(series), XContentType.JSON);

        IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);

        System.out.println(index.toString());
        System.out.println(index.status());
    }

    @Test
    void testGetDocument() throws IOException {
        GetRequest getRequest = new GetRequest("new_index","1");

        // not return source context, only return search data
//        getRequest.fetchSourceContext(new FetchSourceContext(false));
//        getRequest.storedFields("_none_");

        GetResponse documentFields = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(documentFields.getSourceAsString());

    }

    @Test
    void testUpdateDocument() throws IOException {
        Series series = new Series(100,"new hello",new Date());
        UpdateRequest updateRequest = new UpdateRequest("new_index","1");
        ObjectMapper objectMapper = new ObjectMapper();
        updateRequest.doc(objectMapper.writeValueAsString(series),XContentType.JSON);
        UpdateResponse update = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(update);
    }

    @Test
    void testDeleteDocument() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("new_index","1");
        DeleteResponse delete = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(delete.status());

    }

    @Test
    void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();

        List<Series> list = new ArrayList<>();
        list.add(new Series(1,"hello1",new Date()));
        list.add(new Series(2,"hello2",new Date()));
        list.add(new Series(3,"hello3",new Date()));
        list.add(new Series(4,"hello4",new Date()));
        list.add(new Series(5,"hello5",new Date()));

        ObjectMapper objectMapper = new ObjectMapper();
        for (Series series : list) {
            bulkRequest.add(new IndexRequest("new_index")
                    .id(""+series.getSeriesId())
                    .source(objectMapper.writeValueAsString(series),XContentType.JSON));
        }

        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.hasFailures());
    }

    //advanced search
    @Test
    void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("new_index");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();


        searchSourceBuilder.query(QueryBuilders.matchQuery("seriesName","hello1"));

        searchRequest.source(searchSourceBuilder);

        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        for (SearchHit hit : search.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }
}
