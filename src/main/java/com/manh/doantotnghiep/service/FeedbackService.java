package com.manh.doantotnghiep.service;

import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.FeedbackEntity;

public interface FeedbackService {

    public ResultBean getAll() throws Exception;

    /**
     * Gets the by order id.
     *
     * @param id the id
     * @return the by order id
     * @throws Exception the exception
     */
    public ResultBean getById(Integer id) throws Exception;

    /**
     * Adds the.
     *
     * @param entity the entity
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean addFeedback(FeedbackEntity entity) throws Exception;

    /**
     * Update.
     *
     * @param entity the entity
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean updateFeedback(FeedbackEntity entity) throws Exception;

    /**
     * Delete.
     *
     * @param id the id
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean deleteById(Integer id) throws Exception;
}
