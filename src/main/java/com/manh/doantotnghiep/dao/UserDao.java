package com.manh.doantotnghiep.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manh.doantotnghiep.bean.entity.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, Integer>{

    Optional<UserEntity> findByUsername(String userName);
    
}
