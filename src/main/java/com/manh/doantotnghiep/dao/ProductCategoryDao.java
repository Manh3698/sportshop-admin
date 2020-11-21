package com.manh.doantotnghiep.dao;

import com.manh.doantotnghiep.bean.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryDao extends JpaRepository<ProductCategoryEntity, Integer> {

}
