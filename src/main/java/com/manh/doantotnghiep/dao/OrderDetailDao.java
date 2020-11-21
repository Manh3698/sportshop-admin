package com.manh.doantotnghiep.dao;

import com.manh.doantotnghiep.bean.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetailEntity, Integer> {
    List<OrderDetailEntity> findByOrderId(Integer orderId);
}
