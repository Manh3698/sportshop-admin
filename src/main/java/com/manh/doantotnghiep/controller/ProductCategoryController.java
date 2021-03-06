package com.manh.doantotnghiep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.config.LogExecutionTime;
import com.manh.doantotnghiep.service.ProductCategoryService;
import com.manh.doantotnghiep.utils.Constants;

/**
 * The Class ProductCategoryController.
 */
@Controller
@LogExecutionTime
@RequestMapping(value = "/api/productCate")
public class ProductCategoryController {

    /** The product cate service. */
    @Autowired
    private ProductCategoryService productCateService;

    /**
     * Gets the all products cate.
     *
     * @return the all products cate
     * @throws Exception the exception
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getAllProductsCate() throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productCateService.getAll();
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * Gets the all product cate by id.
     *
     * @param id the id
     * @return the all product cate by id
     * @throws Exception the exception
     */

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getAllProductCateById(@PathVariable Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productCateService.getById(id);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * Detele product cate by id.
     *
     * @param id the id
     * @return the response entity
     * @throws Exception the exception
     */
    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> deteleProductCateById(@PathVariable Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productCateService.deleteById(id);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * Adds the product cate.
     *
     * @param entity the entity
     * @return the response entity
     * @throws Exception the exception
     */
    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addProductCate(@RequestBody String json) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productCateService.addProductCategory(json);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * Update product cate.
     *
     * @param entity the entity
     * @return the response entity
     * @throws Exception the exception
     */
    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> updateProductCate(@RequestBody String json) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productCateService.updateProductCategory(json);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
}
