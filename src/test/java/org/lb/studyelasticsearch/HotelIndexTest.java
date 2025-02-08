package org.lb.studyelasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lb.studyelasticsearch.constant.HotelConstant;

import static org.lb.studyelasticsearch.constant.HotelConstant.HOST_URL;

/**
 * @description: RestClient索引库curd测试
 **/
public class HotelIndexTest {
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void testInit() {
        System.out.println(restHighLevelClient);
    }

    // 新增索引
    @Test
    public void testCreateIndex() throws Exception {
        // 1. 创建请求对象
        CreateIndexRequest request = new CreateIndexRequest(HotelConstant.INDEX_NAME);
        // 2. 准备请求参数
        request.source(HotelConstant.MAPPING_TEMPLATE, XContentType.JSON);
        // 3. 发送请求
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
    }

    // 删除索引
    @Test
    public void testDeleteIndex() throws Exception {
        // 1. 创建请求对象
        DeleteIndexRequest request = new DeleteIndexRequest(HotelConstant.INDEX_NAME);
        // 2. 发送请求
        restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
    }

    // 判断索引是否存在
    @Test
    public void testIndexExists() throws Exception {
        GetIndexRequest request = new GetIndexRequest(HotelConstant.INDEX_NAME);
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists ? "索引库已存在!" : "索引库不存在! " );
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
