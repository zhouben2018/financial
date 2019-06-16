package com.zben.saller.controller;

import com.zben.entity.Product;
import com.zben.saller.service.ProductRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zben
 * @Description:
 * @Date: 16:30 2019/6/14
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductRpcService productRpcService;

    @RequestMapping(value = "/{id}")
    public Product findOne(@PathVariable("id") String id) {
        return productRpcService.findOne(id);
    }
}
