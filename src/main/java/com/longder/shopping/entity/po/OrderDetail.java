package com.longder.shopping.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 订单详情实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "order_detail")
public class OrderDetail extends BaseIdEntity {

    /**
     * 买家
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer_id_")
    private SysUser buyer;

    /**
     * 商品
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "goods_id_")
    private Goods goods;

    /**
     * 卖家
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id_")
    private SysUser seller;

    /**
     * 商品数量
     */
    @Column(name = "count_")
    private Integer count;

    /**
     * 总价
     */
    @Column(name = "total_")
    private Double total;


}
