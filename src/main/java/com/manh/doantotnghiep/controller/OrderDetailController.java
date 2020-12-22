package com.manh.doantotnghiep.controller;

import javax.servlet.http.HttpServletRequest;

import com.manh.doantotnghiep.bean.entity.FeedbackEntity;
import com.manh.doantotnghiep.bean.entity.OrderDetailEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.OrderEntity;
import com.manh.doantotnghiep.config.LogExecutionTime;
import com.manh.doantotnghiep.service.OrderDetailService;
import com.manh.doantotnghiep.service.OrderService;
import com.manh.doantotnghiep.service.PaypalService;
import com.manh.doantotnghiep.utils.Constants;
import com.manh.doantotnghiep.utils.DataUtil;
import com.manh.doantotnghiep.utils.PaypalPaymentIntent;
import com.manh.doantotnghiep.utils.PaypalPaymentMethod;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;


@Controller
@LogExecutionTime
@RequestMapping("/api/orderdetail")
public class OrderDetailController {
    /** The order detail service. */
    @Autowired
    private OrderDetailService orderDetailService;


    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Gets the all orders.
     *
     * @return the all orders
     * @throws Exception the exception
     */
    //@PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getAll() throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = orderDetailService.getAll();
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * Adds the product.
     *
     * @param orderDetailEntity the order
     * @return the response entity
     * @throws Exception the exception
     */
    // @PreAuthorize("hasRole('user') or hasRole('aQdmin')")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addOrderDetail(@RequestParam OrderDetailEntity orderDetailEntity) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = orderDetailService.addOrderDetail(orderDetailEntity);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

}
