package org.lb.studyelasticsearch;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.*;
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

import static org.lb.studyelasticsearch.constant.HotelConstant.HOST_URL;

/**
 * @description: RestClient文档curd测试
 */
@SpringBootTest
public class HotelDocumentTest {
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ITbHotelService hotelService;

    @Test
    public void testInit() {
        System.out.println(restHighLevelClient);
    }

    @Test
    public void testCreateIndex() throws Exception {
        // 根据id查询酒店数据
        TbHotel tbHotel = hotelService.selectTbHotelById(5013L);
        // 转换为文档对象
        TbHotelDocVo tbHotelDocVo = new TbHotelDocVo(tbHotel);

        // 1.创建请求对象
        IndexRequest request = new IndexRequest(HotelConstant.INDEX_NAME).id(tbHotelDocVo.getId().toString());
        // 2.准备请求参数
        request.source(JSON.toJSONString(tbHotelDocVo), XContentType.JSON);
        // 3.发送请求
        restHighLevelClient.index(request, RequestOptions.DEFAULT);
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
