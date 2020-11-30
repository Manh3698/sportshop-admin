package com.manh.doantotnghiep.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.UserEntity;
import com.manh.doantotnghiep.config.LogExecutionTime;
import com.manh.doantotnghiep.dao.UserDao;
import com.manh.doantotnghiep.service.UserService;
import com.manh.doantotnghiep.utils.Constants;

/**
 * The Class UserServiceImpl.
 */
@Service
@LogExecutionTime
public class UserServiceImpl implements UserService {

    /** The user dao. */
    @Autowired
    private UserDao userDao;

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    @Override
    public ResultBean getAll() throws Exception {
        List<UserEntity> usersEntity = userDao.findAll();
        return new ResultBean(usersEntity, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Gets the user by id.
     *
     * @param id the id
     * @return the user by id
     * @throws Exception the exception
     */
    @Override
    public ResultBean getUserById(Integer id) throws Exception {
        Optional<UserEntity> userOp = userDao.findById(id);
        if (!userOp.isPresent()) {
            throw new Exception("User by id " + id + " does not exist!");
        }
        UserEntity user = userOp.get();
        return new ResultBean(user, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Update user.
     *
     * @param user the user
     * @return the result bean
     * @throws Exception the exception
     */
    @Override
    public ResultBean updateUser(UserEntity user) throws Exception {
        Optional<UserEntity> userDb = userDao.findById(user.getId());
        if (!userDb.isPresent()) {
            throw new Exception("User by id " + user.getId() + " does not exist!");
        }
        if (!userDb.get().getPassword().equals(new BCryptPasswordEncoder().encode(user.getPassword()))) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        UserEntity entity = userDao.save(user);
        return new ResultBean(entity, Constants.STATUS_201, Constants.MSG_OK);
    }

    /**
     * Adds the user.
     *
     * @param user the user
     * @return the result bean
     * @throws Exception the exception
     */
    @Override
    public ResultBean addUser(UserEntity user) throws Exception {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        UserEntity entity = userDao.save(user);
        return new ResultBean(entity, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Delete userby id.
     *
     * @param id the id
     * @return the result bean
     * @throws Exception the exception
     */
    @Override
    public ResultBean deleteUserbyId(Integer id) throws Exception {
        if (!userDao.existsById(id)) {
            throw new Exception("User by id " + id + " does not exist!");
        }
        userDao.deleteById(id);
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
    }
}