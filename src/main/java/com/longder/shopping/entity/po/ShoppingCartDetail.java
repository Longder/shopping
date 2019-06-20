package com.longder.shopping.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 购物车详情实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "shopping_cart_detail")
public class ShoppingCartDetail extends BaseIdEntity{

    /**
     * 买家
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer_id_")
    @JsonIgnore
    private SysUser buyer;

    /**
     * 商品
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "goods_id_")
    private Goods goods;

    /**
     * 商品数量
     */
    @Column(name = "count_")
    private Integer count;

}
