package org.lb.studyelasticsearch;

import org.apache.http.HttpHost;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;

import static junit.framework.TestCase.assertTrue;
import static org.lb.studyelasticsearch.constant.HotelConstant.HOST_URL;
import static org.lb.studyelasticsearch.constant.HotelConstant.INDEX_NAME;

public class HotelSearchTest {
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void testInit() {
        System.out.println(restHighLevelClient);
    }

    @Test
    public void testMatchAll() throws IOException {
        // 1.创建请求对象
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        // 2.准备请求参数
        searchRequest.source().query(QueryBuilders.matchAllQuery());
        // 3.发送请求
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        Assertions.assertTrue(response.getSuccessfulShards() > 0);

        // 4.解析响应-查询总数
        long totalHits = Objects.requireNonNull(response.getHits().getTotalHits()).value;
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
            System.out.println("=======================");
        }

    }












    // 初始化RestHighLevelClient
    @BeforeEach
    public void init() {
        this.restHighLevelClient = new RestHighLevelClient(RestClient.builder(
                HttpHost.create(HOST_URL)
                // 可以添加多个节点, 集群模式
                // HttpHost.create("http://192.168.121.139:9200")
        ));
    }

    @AfterEach
    public void close() throws Exception {
        this.restHighLevelClient.close();
    }
}
