package com.longder.shopping.repository;


import com.longder.shopping.entity.po.OrderDetail;
import com.longder.shopping.entity.po.SysUser;
import org.omg.CORBA.LongLongSeqHelper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query("select d from OrderDetail d where d.seller = :seller")
    List<OrderDetail> listBySeller(@Param("seller") SysUser seller);
}
