package com.manh.doantotnghiep.controller;

import com.manh.doantotnghiep.config.LogExecutionTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@LogExecutionTime
@RequestMapping(value = "/api/product")
public class ProductController {


}
