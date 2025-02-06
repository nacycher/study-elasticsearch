package org.lb.studyelasticsearch.controller;

import java.util.List;

import org.lb.studyelasticsearch.pojo.TbHotel;
import org.lb.studyelasticsearch.service.ITbHotelService;
import org.lb.studyelasticsearch.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2025-02-06
 */
@Controller
@RequestMapping("/system/hotel")
public class TbHotelController
{
    private String prefix = "system/hotel";

    @Autowired
    private ITbHotelService tbHotelService;

    @GetMapping()
    public String hotel()
    {
        return prefix + "/hotel";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult list(TbHotel tbHotel)
    {
        List<TbHotel> list = tbHotelService.selectTbHotelList(tbHotel);
        return AjaxResult.success(list);
    }


    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbHotel tbHotel)
    {
        return AjaxResult.success(tbHotelService.insertTbHotel(tbHotel));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbHotel tbHotel = tbHotelService.selectTbHotelById(id);
        mmap.put("tbHotel", tbHotel);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbHotel tbHotel)
    {
        return AjaxResult.success(tbHotelService.updateTbHotel(tbHotel));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return AjaxResult.success(tbHotelService.deleteTbHotelByIds(ids));
    }
}
