package com.manh.doantotnghiep.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.UserEntity;
import com.manh.doantotnghiep.service.UserService;
import com.manh.doantotnghiep.service.Impl.ProductServiceImpl;
import com.manh.doantotnghiep.utils.Constants;

@Controller
@RequestMapping("/api/account")
public class AccountController {
    private AuthenticationManager authenticationManager;
    
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getAllUsers() throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = userService.getAll();
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getUserById(@RequestParam Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = userService.getUserById(id);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> deleteUserById(@RequestParam Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = userService.deleteUserbyId(id);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> updateUser(@RequestBody UserEntity user) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = userService.updateUser(user);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addUser(@RequestBody String json) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = userService.addUser(json);
        } catch (Exception e) {
            log.error(e.getMessage());
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> login(String userName, String password) throws Exception {
        ResultBean resultBean = null;
        try {

        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
}
