package com.manh.doantotnghiep.service;

import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.OrderDetailEntity;

/**
 * The Interface OrderDetailService.
 */
public interface OrderDetailService {

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    public ResultBean getAll() throws Exception;

    /**
     * Gets the by order id.
     *
     * @param id the id
     * @return the by order id
     * @throws Exception the exception
     */
    public ResultBean getByOrderId(Integer id) throws Exception;

    /**
     * Adds the.
     *
     * @param entity the entity
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean addOrderDetail(OrderDetailEntity entity) throws Exception;

    /**
     * Update.
     *
     * @param entity the entity
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean updateOrderDetail(OrderDetailEntity entity) throws Exception;

    /**
     * Delete.
     *
     * @param id the id
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean deleteOrderDetailById(Integer id) throws Exception;
}
