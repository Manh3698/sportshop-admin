package com.manh.doantotnghiep.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manh.doantotnghiep.bean.entity.ContactEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDao extends JpaRepository<ContactEntity, Integer>{

}
