package com.manh.doantotnghiep.service.Impl;

import java.util.*;
import java.util.stream.Collectors;

import com.manh.doantotnghiep.bean.entity.RoleEntity;
import com.manh.doantotnghiep.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.UserEntity;
import com.manh.doantotnghiep.config.LogExecutionTime;
import com.manh.doantotnghiep.dao.UserDao;
import com.manh.doantotnghiep.service.UserService;
import com.manh.doantotnghiep.utils.Constants;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class UserServiceImpl.
 */
@Service
@LogExecutionTime
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    /** The user dao. */
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    private ObjectMapper mapper = new ObjectMapper();

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
        Optional<UserEntity> userDb = userDao.findByUsername(user.getUsername());
        if (userDb.isPresent()) {
            throw new Exception("User by user name " + user.getUsername() + " have been exist!");
        }

       Set<RoleEntity> roles = new HashSet<>(roleDao.findAllById(user.getRoles().stream().map(res -> res.getId()).collect(Collectors.toSet())));
        user.setRoles(roles);
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
    
    private UserEntity updateEntity(String json) throws Exception {
        return mapper.readValue(json, UserEntity.class);
    }
    
    @Override
    public boolean isExitsUserName(String userName) throws Exception {
        return userDao.findByUsername(userName).isPresent();
    }

    @Override
    public ResultBean getUserByUsername(String username) throws Exception {
        Optional<UserEntity> userOp = userDao.findByUsername(username);
        if(!userOp.isPresent()) {
            throw new Exception("User by username " + username + " does not exist!");
        }
        UserEntity user = userOp.get();
        return new ResultBean(user, Constants.STATUS_OK, Constants.MSG_OK);
    }
}
