package com.manh.doantotnghiep.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manh.doantotnghiep.bean.entity.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, Integer>{

}
