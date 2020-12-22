package com.manh.doantotnghiep.dao;

import com.manh.doantotnghiep.bean.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findByUserId(Integer userId);

    @Query(value = "SELECT MAX(p.id) FROM products p",  nativeQuery = true)
    Integer getProductHot();

}
