package com.manh.doantotnghiep.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.ProductCategoryEntity;
import com.manh.doantotnghiep.dao.ProductCategoryDao;
import com.manh.doantotnghiep.service.ProductCategoryService;
import com.manh.doantotnghiep.utils.Constants;
import com.manh.doantotnghiep.utils.DataUtil;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);


    /** The mapper. */
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ProductCategoryDao productCateDao;

    @Override
    public ResultBean getAll() throws Exception {
        List<ProductCategoryEntity> productCategoryEntities = productCateDao.findAll();
        return new ResultBean(productCategoryEntities, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean getById(Integer id) throws Exception {
        ProductCategoryEntity productCate = productCateDao.findById(id).get();
        return new ResultBean(productCate, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean deleteById(Integer id) throws Exception {
        productCateDao.deleteById(id);
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean addProductCategory(String json) throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Add Product cate###");
        ProductCategoryEntity productCate = updateEntity(json);
        productCateDao.save(productCate);
        log.info("### End Add Product Cate ###");
        log.info("##########################################");
        return new ResultBean(Constants.STATUS_201, Constants.MSG_OK);
    }

    @Override
    public ResultBean updateProductCategory(String json) throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Update Product Cate ###");
        JsonObject jsonObj = DataUtil.getJsonObject(json);
        Integer id = jsonObj.get("id").getAsInt();
        if (!productCateDao.existsById(id)) {
            throw new Exception("Product Category ID " + id + " does not exits");
        }
        ProductCategoryEntity productCate = updateEntity(json);
        productCateDao.save(productCate);
        log.info("### End Update Product Cate ###");
        log.info("##########################################");
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
    }

    private ProductCategoryEntity updateEntity(String json) throws Exception {
        return mapper.readValue(json, ProductCategoryEntity.class);
    }
}
