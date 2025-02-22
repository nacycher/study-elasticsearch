package org.lb.studyelasticsearch.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.lb.studyelasticsearch.pojo.TbHotel;
import org.lb.studyelasticsearch.pojo.dto.HotelKeySearchDTO;
import org.lb.studyelasticsearch.pojo.vo.PageResultVO;
import org.lb.studyelasticsearch.pojo.vo.TbHotelDocVO;

import java.io.IOException;
import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2025-02-06
 */
public interface ITbHotelService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TbHotel selectTbHotelById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tbHotel 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TbHotel> selectTbHotelList(TbHotel tbHotel);

    /**
     * es - 根据关键字查询酒店列表
     *
     * @param hotelKeySearchDTO 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public PageResultVO selectTbHotelListByEsKey(HotelKeySearchDTO hotelKeySearchDTO);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tbHotel 【请填写功能名称】
     * @return 结果
     */
    public int insertTbHotel(TbHotel tbHotel);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tbHotel 【请填写功能名称】
     * @return 结果
     */
    public int updateTbHotel(TbHotel tbHotel);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteTbHotelByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTbHotelById(Long id);
}
