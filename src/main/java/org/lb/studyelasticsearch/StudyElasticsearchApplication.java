package org.lb.studyelasticsearch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.lb.studyelasticsearch.mapper")
@SpringBootApplication
public class StudyElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyElasticsearchApplication.class, args);
    }

}
