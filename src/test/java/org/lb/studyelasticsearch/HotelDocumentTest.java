package org.lb.studyelasticsearch;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.*;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lb.studyelasticsearch.constant.HotelConstant;
import org.lb.studyelasticsearch.pojo.TbHotel;
import org.lb.studyelasticsearch.pojo.vo.TbHotelDocVo;
import org.lb.studyelasticsearch.service.ITbHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.lb.studyelasticsearch.constant.HotelConstant.HOST_URL;
import static org.lb.studyelasticsearch.constant.HotelConstant.INDEX_NAME;

/**
 * @description: RestClient文档curd测试
 */
@SpringBootTest
public class HotelDocumentTest {
    @Autowired
    private ITbHotelService hotelService;

    private RestHighLevelClient restHighLevelClient;
    private final Long TEST_DOCUMENT_ID = 5013L;

    @Test
    public void testInit() {
        System.out.println(restHighLevelClient);
    }

    // 新增文档
    @Test
    public void testCreateIndex() throws Exception {
        // 根据id查询酒店数据
        TbHotel tbHotel = hotelService.selectTbHotelById(TEST_DOCUMENT_ID);
        // 转换为文档对象
        TbHotelDocVo tbHotelDocVo = new TbHotelDocVo(tbHotel);

        // 1.创建请求对象
        IndexRequest request = new IndexRequest(HotelConstant.INDEX_NAME).id(tbHotelDocVo.getId().toString());
        // 2.准备请求参数
        request.source(JSON.toJSONString(tbHotelDocVo), XContentType.JSON);
        // 3.发送请求
        restHighLevelClient.index(request, RequestOptions.DEFAULT);
    }

    // 查询文档
    @Test
    public void testGetDocument() throws Exception {
        // 1.创建请求对象
        GetRequest request = new GetRequest(INDEX_NAME, TEST_DOCUMENT_ID.toString());
        // 2.获取返回结果
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        // 3.获取文档内容
        String json = response.getSourceAsString();
        // 4. 反序列化
        TbHotelDocVo tbHotelDocVo = JSON.parseObject(json, TbHotelDocVo.class);

        System.out.println(tbHotelDocVo);
    }

    /**
     * 修改文档-全量修改，删除旧文档，新增新文档
     **/
    @Test
    public void testUpdateDocumentAll() throws Exception {

    }

    // 修改文档-局部修改
    @Test
    public void testUpdateDocumentPartial() throws Exception {
        UpdateRequest request = new UpdateRequest(INDEX_NAME, TEST_DOCUMENT_ID.toString());
        request.doc(
                "price", 300,
                "city", "成都");
        restHighLevelClient.update(request, RequestOptions.DEFAULT);
    }

    // 删除文档
    @Test
    public void testDeleteDocument() throws Exception {
        DeleteRequest request = new DeleteRequest(INDEX_NAME, TEST_DOCUMENT_ID.toString());
        restHighLevelClient.delete(request, RequestOptions.DEFAULT);
    }

    // 批量添加文档
    @Test
    public void testBatchCreateDocument() throws Exception {
        // 批量查询酒店数据
        List<TbHotel> tbHotelList = hotelService.selectTbHotelList(null);

        // 1. 准备请求对象bulkRequest
        BulkRequest request = new BulkRequest();
        // 2. 批量添加request(可以是新增request、修改request、删除request)
        tbHotelList.forEach(tbHotel -> {
            TbHotelDocVo tbHotelDocVo = new TbHotelDocVo(tbHotel);
            request.add(new IndexRequest(INDEX_NAME).id(tbHotelDocVo.getId().toString())
                    .source(JSON.toJSONString(tbHotelDocVo), XContentType.JSON));
        });
        // 3.发送请求
        restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
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
