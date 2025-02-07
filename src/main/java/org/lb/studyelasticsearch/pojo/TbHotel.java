package org.lb.studyelasticsearch.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 tb_hotel
 * 
 * @author ruoyi
 * @date 2025-02-06
 */
@TableName("tb_hotel")
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
    private String Longitude;

    /** 酒店图片；例： / img / 1.jpg */
    private String pic;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setPrice(Long price) 
    {
        this.price = price;
    }

    public Long getPrice() 
    {
        return price;
    }

    public void setScore(Long score) 
    {
        this.score = score;
    }

    public Long getScore() 
    {
        return score;
    }

    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }

    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }

    public void setStarName(String starName) 
    {
        this.starName = starName;
    }

    public String getStarName() 
    {
        return starName;
    }

    public void setBusiness(String business) 
    {
        this.business = business;
    }

    public String getBusiness() 
    {
        return business;
    }

    public void setLatitude(String latitude) 
    {
        this.latitude = latitude;
    }

    public String getLatitude() 
    {
        return latitude;
    }

    public void setLongitude(String Longitude) 
    {
        this.Longitude = Longitude;
    }

    public String getLongitude() 
    {
        return Longitude;
    }

    public void setPic(String pic) 
    {
        this.pic = pic;
    }

    public String getPic() 
    {
        return pic;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("address", getAddress())
            .append("price", getPrice())
            .append("score", getScore())
            .append("brand", getBrand())
            .append("city", getCity())
            .append("starName", getStarName())
            .append("business", getBusiness())
            .append("latitude", getLatitude())
            .append("Longitude", getLongitude())
            .append("pic", getPic())
            .toString();
    }
}
