package com.manh.doantotnghiep.service.Impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.OrderDetailEntity;
import com.manh.doantotnghiep.config.LogExecutionTime;
import com.manh.doantotnghiep.dao.OrderDetailDao;
import com.manh.doantotnghiep.service.OrderDetailService;
import com.manh.doantotnghiep.utils.Constants;

@Service
@LogExecutionTime
public class OrderDetailServiceImpl implements OrderDetailService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    public ResultBean getAll() throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Get List Products ###");
        List<OrderDetailEntity> orderDetails = orderDetailDao.findAll();
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### End Get List Products ###");
        return new ResultBean(orderDetails, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean getByOrderId(Integer id) throws Exception {
        Optional<OrderDetailEntity> orderDetailOp = orderDetailDao.findById(id);
        if (!orderDetailOp.isPresent()) {
            throw new Exception("Order id " + id + " does not exist!");
        }
        OrderDetailEntity orderDetailResponse = orderDetailOp.get();
        return new ResultBean(orderDetailResponse, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean addOrderDetail(OrderDetailEntity entity) throws Exception {
        OrderDetailEntity orderDeatilEntity = modelMapper.map(entity, OrderDetailEntity.class);
        OrderDetailEntity orderDetail = orderDetailDao.save(orderDeatilEntity);
        return new ResultBean(orderDetail, Constants.STATUS_201, Constants.MSG_OK);
    }

    @Override
    public ResultBean updateOrderDetail(OrderDetailEntity entity) throws Exception {
        if (!orderDetailDao.existsById(entity.getId())) {
            throw new Exception("Order Detail by Id " + entity.getId() + " does not exist!");
        }
        OrderDetailEntity orderDeatilEntity = modelMapper.map(entity, OrderDetailEntity.class);
        OrderDetailEntity orderDetail = orderDetailDao.save(orderDeatilEntity);
        return new ResultBean(orderDetail, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean deleteOrderDetailById(Integer id) throws Exception {
        if (!orderDetailDao.existsById(id)) {
            throw new Exception("Order Detail by Id " + id + " does not exist!");
        }
        orderDetailDao.deleteById(id);
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_DELETE_OK);
    }

}
