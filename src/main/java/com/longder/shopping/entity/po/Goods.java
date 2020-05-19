package com.longder.shopping.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.longder.shopping.entity.enumeration.GoodsType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

/**
 * 商品实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Goods extends BaseIdEntity {
    /**
     * 商品名称
     */
    @Column(name = "name_")
    private String name;

    /**
     * 商品类型
     */
    @Column(name = "type_")
    @Enumerated(EnumType.STRING)
    private GoodsType type;

    @Transient
    private GoodsType test;

    /**
     * 商品单价
     */
    @Column(name = "unit_price_")
    private Double unitPrice;

    /**
     * 库存数量
     */
    @Column(name = "count_")
    private Integer count;

    /**
     * 商品图片数据 ，BASE64值
     */
    @Lob
    @Column(name = "image_",columnDefinition="longtext")
    private String image;

    /**
     * 描述
     */
    @Column(name = "describe_")
    private String describe;

    /**
     * 卖家
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id_")
    @JsonIgnore
    private SysUser seller;

    /**
     * 上传的图片文件
     */
    @Transient
    private MultipartFile imageFile;
}
