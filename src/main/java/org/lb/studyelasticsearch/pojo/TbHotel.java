package org.lb.studyelasticsearch.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 tb_hotel
 * 
 * @author ruoyi
 * @date 2025-02-06
 */
@Getter
@Setter
@ToString
public class TbHotel
{
    private static final long serialVersionUID = 1L;

    /** 酒店id */
    private Long id;

    /** 酒E名称;例: 7天酒店 */
    private String name;

    /** 酒店地址;例:航头路 */
    private String address;

    /** 酒店价格;例: 329 */
    private Long price;

    /** 酒店评分:例: 45,就是4.5分 */
    private Long score;

    /** 酒店品牌;例: 如家 */
    private String brand;

    /** 所在城市;例：上海 */
    private String city;

    /** 酒店星级，从低到是：1星到5星，1钻到5钻 */
    private String starName;

    /** 商圈; 例: 虹桥 */
    private String business;

    /** 度;例: 31.2497 */
    private String latitude;

    /** 经度;例: 120.3925 */
    private String longitude;

    /** 酒店图片；例： / img / 1.jpg */
    private String pic;
}
