package com.manh.doantotnghiep.dao;

import com.manh.doantotnghiep.bean.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findByCategoryId(Integer categoryId);
}
