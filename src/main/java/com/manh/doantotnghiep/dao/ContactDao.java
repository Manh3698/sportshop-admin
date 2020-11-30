package com.manh.doantotnghiep.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manh.doantotnghiep.bean.entity.ContactEntity;

public interface ContactDao extends JpaRepository<ContactEntity, Integer>{

}
