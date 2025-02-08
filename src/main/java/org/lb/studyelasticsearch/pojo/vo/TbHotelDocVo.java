package org.lb.studyelasticsearch.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lb.studyelasticsearch.pojo.TbHotel;

@Data
@NoArgsConstructor
public class TbHotelDocVo {
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

    /** 经纬度;例: 31.2304,121.4737 */
    private String location;

    /** 酒店图片；例： / img / 1.jpg */
    private String pic;

    public TbHotelDocVo(TbHotel tbHotel) {
        this.id = tbHotel.getId();
        this.name = tbHotel.getName();
        this.address = tbHotel.getAddress();
        this.price = tbHotel.getPrice();
        this.score = tbHotel.getScore();
        this.brand = tbHotel.getBrand();
        this.city = tbHotel.getCity();
        this.starName = tbHotel.getStarName();
        this.business = tbHotel.getBusiness();
        this.location = tbHotel.getLatitude() + "," + tbHotel.getLongitude();
        this.pic = tbHotel.getPic();

    }
}
