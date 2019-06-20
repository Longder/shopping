package com.longder.shopping.repository;

import com.longder.shopping.entity.po.Goods;
import com.longder.shopping.entity.po.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface GoodsRepository extends JpaRepository<Goods,Long> {

    @Query("select g from Goods g where g.seller = :seller")
    List<Goods> listByUser(@Param("seller") SysUser seller);
}
