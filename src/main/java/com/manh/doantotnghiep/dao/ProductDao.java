package com.manh.doantotnghiep.dao;

import com.manh.doantotnghiep.bean.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findByCategoryId(Integer categoryId);
    
    @Query(value = "SELECT MAX(p.id) FROM products p",  nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT * FROM products p ORDER BY p.quantity_order LIMIT 10",  nativeQuery = true)
    List<ProductEntity> getHotProduct();
}
