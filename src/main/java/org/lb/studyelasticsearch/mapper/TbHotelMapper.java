package org.lb.studyelasticsearch.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lb.studyelasticsearch.pojo.TbHotel;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-06
 */
public interface TbHotelMapper
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
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTbHotelById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbHotelByIds(String[] ids);
}
