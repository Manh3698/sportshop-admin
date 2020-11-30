package com.manh.doantotnghiep.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manh.doantotnghiep.bean.entity.FeedbackEntity;

@Repository
public interface FeedbackDao extends JpaRepository<FeedbackEntity, Integer>{

}
