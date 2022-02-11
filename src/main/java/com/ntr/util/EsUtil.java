package com.ntr.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class EsUtil<T> {

    private final RestHighLevelClient client;
    private final ObjectMapper objectMapper;

    public EsUtil(@Qualifier("restHighLevelClient") RestHighLevelClient client) {
        this.client = client;
        objectMapper = new ObjectMapper();
    }

    @SneakyThrows(IOException.class)
    public boolean insertDoc(String indexName, String id, T data){
        IndexRequest indexRequest = new IndexRequest(indexName);
        indexRequest.id(id);
        indexRequest.timeout(TimeValue.timeValueSeconds(2));
        indexRequest.source(objectMapper.writeValueAsString(data), XContentType.JSON);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        return indexResponse.status() == RestStatus.CREATED || indexResponse.status() == RestStatus.OK;
    }

    @SneakyThrows(IOException.class)
    public boolean insertBulkDoc(String indexName, List<String> ids, List<T> dataList){
        BulkRequest bulkRequest = new BulkRequest();

        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0;i < ids.size();i++) {
            bulkRequest.add(new IndexRequest(indexName)
                    .id(ids.get(i))
                    .source(objectMapper.writeValueAsString(dataList.get(i)),XContentType.JSON));
        }

        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        return bulkResponse.status() == RestStatus.OK;
    }

    @SneakyThrows(IOException.class)
    public boolean updateDoc(String indexName, String id, T data){
        UpdateRequest updateRequest = new UpdateRequest(indexName,id);
        updateRequest.doc(objectMapper.writeValueAsString(data),XContentType.JSON);
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        return updateResponse.status() == RestStatus.OK;
    }

    @SneakyThrows(IOException.class)
    public boolean deleteDoc(String indexName, String id){
        DeleteRequest deleteRequest = new DeleteRequest(indexName,id);
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        return deleteResponse.status() == RestStatus.OK;
    }

}
