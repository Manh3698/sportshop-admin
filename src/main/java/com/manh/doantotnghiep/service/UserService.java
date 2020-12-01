package com.manh.doantotnghiep.service;

import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.UserEntity;

public interface UserService {
    
    public ResultBean getAll() throws Exception;

    public ResultBean getUserById(Integer id) throws Exception;

    public ResultBean updateUser(UserEntity user) throws Exception;

    public ResultBean addUser(String json) throws Exception;

    public ResultBean deleteUserbyId(Integer id) throws Exception;

}
