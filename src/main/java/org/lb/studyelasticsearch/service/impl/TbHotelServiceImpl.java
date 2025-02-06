package org.lb.studyelasticsearch.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.lb.studyelasticsearch.mapper.TbHotelMapper;
import org.lb.studyelasticsearch.pojo.TbHotel;
import org.lb.studyelasticsearch.service.ITbHotelService;

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
