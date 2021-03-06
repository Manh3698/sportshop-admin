package com.manh.doantotnghiep.service.Impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.ImageEntity;
import com.manh.doantotnghiep.bean.entity.ProductEntity;
import com.manh.doantotnghiep.config.LogExecutionTime;
import com.manh.doantotnghiep.dao.ImageDao;
import com.manh.doantotnghiep.dao.ProductDao;
import com.manh.doantotnghiep.service.ImageStorageService;
import com.manh.doantotnghiep.service.ProductService;
import com.manh.doantotnghiep.utils.Constants;
import com.manh.doantotnghiep.utils.DataUtil;

/**
 * The Class ProductServiceImpl.
 */
@Service
@LogExecutionTime
public class ProductServiceImpl implements ProductService {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    /** The mapper. */
    private ObjectMapper mapper = new ObjectMapper();

    /** The product dao. */
    @Autowired
    private ProductDao productDao;

    /** The image storage service. */
    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private ImageDao imageDao;

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    @Override
    public ResultBean getAll() throws Exception {
//        Authentication authe = SecurityContextHolder.getContext().getAuthentication();
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Get List Products ###");
        List<ProductEntity> products = productDao.findAll();
        log.info("### End Get List Products ###");
        return new ResultBean(products, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Gets the products by cate id.
     *
     * @param cateId the cate id
     * @return the products by cate id
     * @throws Exception the exception
     */
    @Override
    public ResultBean getProductsByCateId(Integer cateId) throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Get List Products By Cate Id ###");
        List<ProductEntity> products = productDao.findByCategoryId(cateId);
        log.info("### End Get List Products By Cate Id ###");
        log.info("##########################################");
        return new ResultBean(products, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Gets the by id.
     *
     * @param id the id
     * @return the by id
     * @throws Exception the exception
     */
    @Override
    public ResultBean getById(Integer id) throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Get List Product By Id ###");
        ProductEntity product = productDao.findById(id).get();
        productDao.save(product);
        log.info("### End Get List Product By Id ###");
        log.info("##########################################");
        return new ResultBean(product, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Delete by id.
     *
     * @param id the id
     * @return the boolean
     * @throws Exception the exception
     */
    @Override
    public ResultBean deleteById(Integer id) throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Delete Product By Id ###");
        productDao.deleteById(id);
        log.info("### End Delete Product By Id ###");
        log.info("##########################################");
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Adds the product.
     *
     * @param json the json
     * @param files the files
     * @return the result bean
     * @throws Exception the exception
     */
    @Override
    public ResultBean addProduct(String json, MultipartFile[] files) throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Add Product By Id ###");
        Set<ImageEntity> images = new HashSet<ImageEntity>();
        ProductEntity productEntity = updateEntity(json);
        Integer maxId = productDao.getMaxId();
        maxId = !Objects.isNull(maxId) ? maxId : 0;
        try {
            for (MultipartFile file : files) {
                String fileName = imageStorageService.save(file);
                ImageEntity image = new ImageEntity();
                image.setFileName(fileName);
                image.setParentId(maxId + 1);
                image.setType(Constants.TYPE_PRODUCT);
                imageDao.save(image);
            }
        } catch (Exception e) {
            throw new IOException("Save file fail!");
        }

        productEntity.setImages(images);
        productDao.save(productEntity);
        log.info("### End Add Product By Id ###");
        log.info("##########################################");
        return new ResultBean(Constants.STATUS_201, Constants.MSG_OK);
    }

    /**
     * Update product.
     *
     * @param json the json
     * @return the result bean
     * @throws Exception the exception
     */
//    @Override
//    public ResultBean updateProduct(String json, MultipartFile[] files) throws Exception {
//        log.info("##                                      ##");
//        log.info("##########################################");
//        log.info("### Start Update Product By Id ###");
//        JsonObject jsonObj = DataUtil.getJsonObject(json);
//        Integer id = jsonObj.get("id").getAsInt();
//        Optional<ProductEntity> productOp = productDao.findById(id);
//        if (!productOp.isPresent()) {
//            throw new Exception("Product Id " + id + " does not exist!");
//        }
//        ProductEntity productDb = productOp.get();
//        ProductEntity productEntity = updateEntity(json);
//        if (Objects.isNull(files)) {
//            productEntity.setImages(productDb.getImages());
//        }
//        productDao.save(productEntity);
//        log.info("### End Update Product By Id ###");
//        log.info("##########################################");
//        return new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
//    }
    @Override
    public ResultBean updateProduct(String json, MultipartFile[] files) throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Update Product By Id ###");
        JsonObject jsonObj = DataUtil.getJsonObject(json);
        Integer id = jsonObj.get("id").getAsInt();
        Optional<ProductEntity> productOp = productDao.findById(id);
        if (!productOp.isPresent()) {
            throw new Exception("Product Id " + id + " does not exist!");
        }
        ProductEntity productDb = productOp.get();
        ProductEntity productEntity = updateEntity(json);
        productDb.setImages(null);
        if (Objects.isNull(files)) {
            productEntity.setImages(productDb.getImages());
        }
        imageDao.deleteImageByTypeAndParentId(Constants.TYPE_PRODUCT, id);
        try {
            for (MultipartFile file : files) {
                String fileName = imageStorageService.save(file);
                ImageEntity image = new ImageEntity();
                image.setFileName(fileName);
                image.setParentId(id);
                image.setType(Constants.TYPE_PRODUCT);
                imageDao.save(image);
            }
        } catch (Exception e) {
            throw new IOException("Save file fail!");
        }

        //productEntity.setImages(new HashSet<>(listImg));
        productDao.save(productEntity);
        log.info("### End Update Product By Id ###");
        log.info("##########################################");
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Update entity.
     *
     * @param json the json
     * @return the product entity
     * @throws Exception the exception
     */
    private ProductEntity updateEntity(String json) throws Exception {
        return mapper.readValue(json, ProductEntity.class);
    }

    @Override
    public ResultBean getHotProduct() throws Exception {
        List<ProductEntity> hotProducts = productDao.getHotProduct();
        return new ResultBean(hotProducts, Constants.STATUS_OK, Constants.MSG_OK);
    }
}
