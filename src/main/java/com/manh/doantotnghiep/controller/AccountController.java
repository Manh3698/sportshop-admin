package com.manh.doantotnghiep.controller;


import com.manh.doantotnghiep.bean.Login;
import com.manh.doantotnghiep.config.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.manh.doantotnghiep.bean.AuthenTokenResponse;
import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.UserEntity;
import com.manh.doantotnghiep.config.JwtTokenProvider;
import com.manh.doantotnghiep.service.UserService;
import com.manh.doantotnghiep.utils.Constants;

@Controller
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailService customUserDetailService;
//    @PreAuthorize("hasRole('admin')")
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
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getUserById(@PathVariable Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = userService.getUserById(id);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/getUserByUsername", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getUserById(@RequestParam String username) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = userService.getUserByUsername(username);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> deleteUserById(@PathVariable Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = userService.deleteUserbyId(id);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('user')")
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
    public ResponseEntity<ResultBean> addUser(@RequestBody UserEntity user) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = userService.addUser(user);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> login(@RequestBody Login login) throws Exception {
        String jwt = null;
        UserDetails user = null;
        if (userService.isExitsUserName(login.getUserName())) {
            try {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUserName(), login.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                user = customUserDetailService.loadUserByUsername(login.getUserName());
                jwt = tokenProvider.generateToken(authentication);

            } catch (Exception e) {
                throw new Exception(e.getCause());
            }
        }
        return ResponseEntity.ok(new AuthenTokenResponse(jwt, user.getUsername(), user.getAuthorities()));
    }
    
    @RequestMapping(value = "/resetPass", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> resetPass(String email) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = userService.resetPass(email);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
}
