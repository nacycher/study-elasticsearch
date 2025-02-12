package org.lb.studyelasticsearch.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.lb.studyelasticsearch.pojo.dto.HotelKeySearchDTO;
import org.lb.studyelasticsearch.pojo.vo.PageResultVO;
import org.lb.studyelasticsearch.pojo.vo.TbHotelDocVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.lb.studyelasticsearch.mapper.TbHotelMapper;
import org.lb.studyelasticsearch.pojo.TbHotel;
import org.lb.studyelasticsearch.service.ITbHotelService;

import static org.lb.studyelasticsearch.constant.HotelConstant.INDEX_NAME;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-06
 */
@Service
public class TbHotelServiceImpl implements ITbHotelService 
{
    @Autowired
    private TbHotelMapper tbHotelMapper;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TbHotel selectTbHotelById(Long id)
    {
        return tbHotelMapper.selectTbHotelById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tbHotel 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TbHotel> selectTbHotelList(TbHotel tbHotel)
    {
        return tbHotelMapper.selectTbHotelList(tbHotel);
    }


    /**
     * 根据关键词搜索酒店列表
     *
     * @param hotelKeySearchDTO 包含关键词和分页信息的DTO对象
     * @return 包含酒店信息的分页结果
     */
    @Override
    public PageResultVO selectTbHotelListByEsKey(HotelKeySearchDTO hotelKeySearchDTO){
        // 创建一个新的SearchRequest对象，指定索引名称
        SearchRequest request = new SearchRequest(INDEX_NAME);
        // 如果hotelKeySearchDTO为空或者关键词为空，则查询所有文档
        if (ObjectUtils.isEmpty(hotelKeySearchDTO) || ObjectUtils.isEmpty(hotelKeySearchDTO.getKey())) {
            request.source().query(QueryBuilders.matchAllQuery());
        } else {
            // 否则，根据关键词进行查询
            request.source().query(QueryBuilders.matchQuery("all", hotelKeySearchDTO.getKey()));
        }
        // 设置分页信息
        request.source().from((hotelKeySearchDTO.getPage() - 1) * hotelKeySearchDTO.getSize());
        request.source().size(hotelKeySearchDTO.getSize());
        // 初始化SearchResponse对象为null
        SearchResponse response = null;
        try {
            // 执行搜索请求
            response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            // 如果发生异常，抛出运行时异常
            throw new RuntimeException(e);
        }
        // 处理搜索响应并返回结果
        return handelEsResponse(response);
    }


    private PageResultVO handelEsResponse(SearchResponse response) {
        //4.1解析响应
        SearchHits searchHits = response.getHits();
        //4.2获取总记录数
        long value = Objects.requireNonNull(searchHits.getTotalHits()).value;
        //4.3获取文档数组
        List<TbHotelDocVO> hotels = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            //获取文档source
            String json = hit.getSourceAsString();
            TbHotelDocVO hotelDoc = JSON.parseObject(json, TbHotelDocVO.class);
            //4.4处理高亮
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (CollectionUtils.isNotEmpty(highlightFields)) {
                HighlightField highlightField = highlightFields.get("name");
                if (highlightField != null) {
                    String name = highlightField.getFragments()[0].toString();
                    hotelDoc.setName(name);
                }
            }
            // 处理距离显示
            Object[] sortValues = hit.getSortValues();
            if (ObjectUtils.isNotEmpty(sortValues)) {
                Object sortValue = sortValues[0];
                hotelDoc.setDistance(sortValue);
            }
            hotels.add(hotelDoc);
        }
        return new PageResultVO(value, hotels);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param tbHotel 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTbHotel(TbHotel tbHotel)
    {
        return tbHotelMapper.insertTbHotel(tbHotel);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param tbHotel 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTbHotel(TbHotel tbHotel)
    {
        return tbHotelMapper.updateTbHotel(tbHotel);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTbHotelByIds(String ids)
    {
        return tbHotelMapper.deleteTbHotelByIds(toStrArray(ids));
    }

    /**
     * 转换为String数组<br>
     *
     * @param str 被转换的值
     * @return 结果
     */
    public static String[] toStrArray(String str)
    {
        if (StringUtils.isEmpty(str))
        {
            return new String[] {};
        }
        return toStrArray(",", str);
    }

    /**
     * 转换为String数组<br>
     *
     * @param split 分隔符
     * @param split 被转换的值
     * @return 结果
     */
    public static String[] toStrArray(String split, String str)
    {
        return str.split(split);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTbHotelById(Long id)
    {
        return tbHotelMapper.deleteTbHotelById(id);
    }
}
