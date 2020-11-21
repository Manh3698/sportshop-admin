package com.manh.doantotnghiep.service.Impl;

import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.ProductEntity;
import com.manh.doantotnghiep.config.LogExecutionTime;
import com.manh.doantotnghiep.dao.ProductDao;
import com.manh.doantotnghiep.service.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.util.List;

@Service
@LogExecutionTime
public class ProductServiceImpl implements ProductService {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    static ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ProductDao productDao;

    @Override
    public ResultBean getAll() throws Exception {
//        log.info("##                                      ##");
//        log.info("##########################################");
//        log.info("### Start Get List Products ###");
//        List<ProductEntity> products = productDao.findAll();
//        List<ProductResponse> response = new ArrayList<ProductResponse>();
//        products.forEach(product -> {
//            ProductResponse productResponse =  modelMapper.map(product, ProductResponse.class);
//            response.add(productResponse);
//        });
//        log.info("### End Get List Products ###");
//        return new ResultBean(response, Constants.STATUS_OK, Constants.MSG_OK);
        return null;
    }

    @Override
    public ResultBean getProductsByCateId(Integer CateId) throws Exception {
        return null;
    }

    @Override
    public ResultBean getById(Integer id) throws Exception {
        return null;
    }

    @Override
    public ResultBean deleteById(Integer id) throws Exception {
        return null;
    }

    @Override
    public ResultBean addProduct(String json) throws Exception {
        return null;
    }

    @Override
    public ResultBean updateProduct(String json) throws Exception {
        return null;
    }
}
