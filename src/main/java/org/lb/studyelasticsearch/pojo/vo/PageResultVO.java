package org.lb.studyelasticsearch.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResultVO {
    private Long total;
    private List<TbHotelDocVO> hotels;
}
