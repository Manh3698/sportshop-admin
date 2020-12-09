package com.manh.doantotnghiep.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.OrderDetailEntity;
import com.manh.doantotnghiep.bean.entity.OrderEntity;
import com.manh.doantotnghiep.bean.entity.ProductEntity;
import com.manh.doantotnghiep.config.LogExecutionTime;
import com.manh.doantotnghiep.dao.OrderDao;
import com.manh.doantotnghiep.dao.OrderDetailDao;
import com.manh.doantotnghiep.service.OrderService;
import com.manh.doantotnghiep.utils.Constants;
import com.manh.doantotnghiep.utils.DataUtil;

/**
 * The Class OrderServiceImpl.
 */
@Service
@LogExecutionTime
public class OrderServiceImpl implements OrderService {

    /** The order dao. */
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    @Override
    public ResultBean getAll() throws Exception {
        List<OrderEntity> orders = orderDao.findAll();
        return new ResultBean(orders, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Gets the order by id.
     *
     * @param id the id
     * @return the order by id
     * @throws Exception the exception
     */
    @Override
    public ResultBean getOrderById(Integer id) throws Exception {
        Optional<OrderEntity> orderOp = orderDao.findById(id);
        if (!orderOp.isPresent()) {
            throw new Exception("Order Id " + id + " does not exist!");
        }
        OrderEntity order = orderOp.get();
        return new ResultBean(order, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Gets the order by user id.
     *
     * @param userId the user id
     * @return the order by user id
     * @throws Exception the exception
     */
    @Override
    public ResultBean getOrderByUserId(Integer userId) throws Exception {
        List<OrderEntity> orders = orderDao.findByUserId(userId);
        return new ResultBean(orders, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Adds the order.
     *
     * @param entity the entity
     * @return the result bean
     * @throws Exception the exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBean addOrder(String json) throws Exception {
        OrderEntity order = updateOrderEntity(json);
        order.getOrderDetails().forEach(res -> res.setOrder(order));
        OrderEntity orderSave = orderDao.save(order);
        return new ResultBean(orderSave, Constants.STATUS_201, Constants.MSG_OK);
    }

    /**
     * Update order.
     *
     * @param entity the entity
     * @return the result bean
     * @throws Exception the exception
     */
    @Override
    public ResultBean updateOrder(OrderEntity entity) throws Exception {
        if (!orderDao.existsById(entity.getId())) {
            throw new Exception("Order Id " + entity.getId() + " does not exist!");
        }
        entity.setStatus(true);
        OrderEntity order = orderDao.save(entity);
        return new ResultBean(order, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Delete by id.
     *
     * @param id the id
     * @return the result bean
     * @throws Exception the exception
     */
    @Override
    public ResultBean deleteById(Integer id) throws Exception {
        if (!orderDao.existsById(id)) {
            throw new Exception("Order Id " + id + " does not exist!");
        }
        List<OrderDetailEntity> orderDetails = orderDetailDao.findByOrderId(id);
        orderDetails.forEach(order -> {
            orderDetailDao.deleteById(order.getId());
        });
        orderDao.deleteById(id);
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
    }

    private OrderEntity updateOrderEntity(String json) throws Exception {
        return mapper.readValue(json, OrderEntity.class);
    }

    private OrderDetailEntity updateOrderDetailEntity(String json) throws Exception {
        return mapper.readValue(json, OrderDetailEntity.class);
    }

}
