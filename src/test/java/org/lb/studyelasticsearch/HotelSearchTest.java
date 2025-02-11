package org.lb.studyelasticsearch;

import lombok.NonNull;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;

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
        printSearchResult(response);
    }

    @Test
    public void testMatch() throws IOException {
        // 1.创建请求对象
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        // 2.准备请求参数
        searchRequest.source().query(QueryBuilders.matchQuery("all", "酒店"));
        // 3.发送请求
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        Assertions.assertTrue(response.getSuccessfulShards() > 0);

        // 4.解析响应-查询总数
        printSearchResult(response);
    }

    @Test
    public void testTerm() throws IOException {
        // 1.创建请求对象
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        // 2.准备请求参数
        searchRequest.source().query(QueryBuilders.termQuery("city", "上海"));
        // 3.发送请求
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        Assertions.assertTrue(response.getSuccessfulShards() > 0);

        // 4.解析响应-查询总数
        printSearchResult(response);
    }

    @Test
    public void testRange() throws IOException {
        // 1.创建请求对象
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        // 2.准备请求参数
        searchRequest.source().query(QueryBuilders.rangeQuery("price").gte(600).lte(800));
        // 3.发送请求
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        Assertions.assertTrue(response.getSuccessfulShards() > 0);

        // 4.解析响应-查询总数
        printSearchResult(response);
    }


    @Test
    public void testBooleanQuery() throws IOException {
        // 1.创建请求对象
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        // 2.准备请求参数
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("city", "上海"));
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").lte(500));
        searchRequest.source().query(boolQueryBuilder);
        // 3.发送请求
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        Assertions.assertTrue(response.getSuccessfulShards() > 0);

        // 4.解析响应-查询总数
        printSearchResult(response);
    }

    @Test
    public void testPageSortQuery() throws IOException {
        // 1.创建请求对象
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        // 2.准备请求参数
        searchRequest.source().query(
                        QueryBuilders
                                .matchAllQuery())
                .from(0).size(10)
                .sort("price", SortOrder.ASC);
        // 3.发送请求
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        Assertions.assertTrue(response.getSuccessfulShards() > 0);

        // 4.解析响应-查询总数
        printSearchResult(response);
    }

    @Test
    public void testMultiMatch() throws IOException {
        // 1.创建请求对象
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        // 2.准备请求参数
        searchRequest.source().query(QueryBuilders.multiMatchQuery("酒店", "name", "brand"));
        // 3.发送请求
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        Assertions.assertTrue(response.getSuccessfulShards() > 0);

        // 4.解析响应-查询总数
        printSearchResult(response);
    }

    @Test
    public void testHighLight() throws IOException {
        // 1.创建请求对象
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        // 2.准备请求参数
        searchRequest.source()
                .query(QueryBuilders.multiMatchQuery("酒店", "name", "brand"))
                .highlighter(new HighlightBuilder().field("name").requireFieldMatch(false));
        // 3.发送请求
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        Assertions.assertTrue(response.getSuccessfulShards() > 0);

        System.out.println(response);
    }

    public void printSearchResult(@NonNull SearchResponse response) {
        // 解析响应-查询总数
        long totalHits = Objects.requireNonNull(response.getHits().getTotalHits()).value;
        System.out.println("共查询到" + totalHits + "条数据:");
        // 解析响应-查询结果
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
