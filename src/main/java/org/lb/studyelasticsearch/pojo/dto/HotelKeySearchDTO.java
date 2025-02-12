package org.lb.studyelasticsearch.pojo.dto;

import lombok.Data;

@Data
public class HotelKeySearchDTO {
    private String key;
    private Integer page;
    private Integer size;
    private String sortBy;
}
