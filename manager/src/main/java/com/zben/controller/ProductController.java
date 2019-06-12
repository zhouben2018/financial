package com.zben.controller;

import com.zben.entity.Product;
import com.zben.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zben
 * @Description:
 * @Date: 10:23 2019/6/12
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private static Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product) {
        LOG.info("添加产品...入参 product[{}]",product);
        Product result = productService.add(product);
        LOG.info("添加产品...结果 product[{}]",result);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product findOne(@PathVariable("id") String id) {
        LOG.info("查询单个产品...入参 id[{}]",id);
        Product result = productService.findOne(id);
        LOG.info("查询单个产品...结果 product[{}]",result);
        return result;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<Product> query(String ids, BigDecimal minRewardRate, BigDecimal maxRewardRate, String status,
                               @RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        LOG.info("查询多个产品...入参 ids[{}]，minRewardRate[{}], maxRewardRate[{}], status[{}], pageNum[{}], pageSize[{}]"
                ,ids, minRewardRate, maxRewardRate, status, pageNum, pageSize);
        List<String> idList = null, statusList = null;
        if (!StringUtils.isEmpty(ids)) {
            idList = Arrays.asList(ids.split(","));
        }
        if (!StringUtils.isEmpty(status)) {
            statusList = Arrays.asList(status.split(","));
        }
        Pageable pageable = new PageRequest(pageNum, pageSize);
        Page<Product> page = productService.query(idList, minRewardRate, maxRewardRate, statusList, pageable);
        LOG.info("查询多个产品...结果result[{}]", page);
        return page;
    }
}
