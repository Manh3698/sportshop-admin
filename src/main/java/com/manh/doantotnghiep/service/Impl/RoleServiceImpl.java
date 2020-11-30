package com.manh.doantotnghiep.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.RoleEntity;
import com.manh.doantotnghiep.config.LogExecutionTime;
import com.manh.doantotnghiep.dao.RoleDao;
import com.manh.doantotnghiep.service.RoleService;
import com.manh.doantotnghiep.utils.Constants;

@Service
@LogExecutionTime
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public ResultBean getAll() throws Exception {
        List<RoleEntity> roles = roleDao.findAll();
        return new ResultBean(roles, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean getRoleById(Integer id) throws Exception {
        if (!roleDao.existsById(id)) {
            throw new Exception("Role by id " + id + " does not exist");
        }
        RoleEntity role = roleDao.findById(id).get();
        return new ResultBean(role, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean getRoleByUserId(Integer userId) throws Exception {
        return null;
    }

    @Override
    public ResultBean addRole(RoleEntity entity) throws Exception {
        RoleEntity role = roleDao.save(entity);
        return new ResultBean(role, Constants.STATUS_201, Constants.MSG_OK);
    }

    @Override
    public ResultBean updateRole(RoleEntity entity) throws Exception {
        if (!roleDao.existsById(entity.getId())) {
            throw new Exception("Role by id " + entity.getId() + " does not exist");
        }
        RoleEntity role = roleDao.save(entity);
        return new ResultBean(role, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean deleteRoleById(Integer id) throws Exception {
        if (!roleDao.existsById(id)) {
            throw new Exception("Role by id " + id + " does not exist");
        }
        roleDao.deleteById(id);
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_DELETE_OK);
    }

}
