package com.manh.doantotnghiep.service;

import java.util.List;

import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.OrderDetailEntity;
import com.manh.doantotnghiep.bean.entity.OrderEntity;

/**
 * The Interface OrderService.
 */
public interface OrderService {

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    public ResultBean getAll() throws Exception;

    /**
     * Gets the order by id.
     *
     * @param id the id
     * @return the order by id
     * @throws Exception the exception
     */
    public ResultBean getOrderById(Integer id) throws Exception;

    /**
     * Gets the order by user id.
     *
     * @param userId the user id
     * @return the order by user id
     * @throws Exception the exception
     */
    public ResultBean getOrderByUserId(Integer userId) throws Exception;

    /**
     * Adds the order.
     *
     * @param entity the entity
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean addOrder(OrderEntity entity, List<OrderDetailEntity> orderDetails) throws Exception;

    /**
     * Update order.
     *
     * @param entity the entity
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean updateOrder(OrderEntity entity) throws Exception;

    /**
     * Delete by id.
     *
     * @param id the id
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean deleteById(Integer id) throws Exception;

}
