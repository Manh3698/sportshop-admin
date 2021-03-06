package com.manh.doantotnghiep.service;

import com.manh.doantotnghiep.bean.ResultBean;

/**
 * The Interface ProductCategoryService.
 */
public interface ProductCategoryService {

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    public ResultBean getAll() throws Exception;

    /**
     * Gets the by id.
     *
     * @param id the id
     * @return the by id
     * @throws Exception the exception
     */
    public ResultBean getById(Integer id) throws Exception;

    /**
     * Delete by id.
     *
     * @param id the id
     * @return the boolean
     * @throws Exception the exception
     */
    public ResultBean deleteById(Integer id) throws Exception;

    /**
     * Adds the product.
     *
     * @param json the json
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean addProductCategory(String json) throws Exception;

    /**
     * Update product.
     *
     * @param json the json
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean updateProductCategory(String json) throws Exception;
}
