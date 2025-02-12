package org.lb.studyelasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.lb.studyelasticsearch.constant.HotelConstant.HOST_URL;

@MapperScan("org.lb.studyelasticsearch.mapper")
@SpringBootApplication
public class StudyElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyElasticsearchApplication.class, args);
    }

    @Bean
    public RestHighLevelClient client() {
        return new RestHighLevelClient(RestClient.builder(
                HttpHost.create(HOST_URL)
                // 可以添加多个节点, 集群模式
                // HttpHost.create("http://192.168.121.139:9200")
        ));
    }
}
