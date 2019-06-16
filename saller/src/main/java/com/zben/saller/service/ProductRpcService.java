package com.zben.saller.service;

import com.zben.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zben
 * @Description:
 * @Date: 11:12 2019/6/13
 */
@Service
public class ProductRpcService implements ApplicationListener<ContextRefreshedEvent>{

    private static Logger LOG = LoggerFactory.getLogger(ProductRpcService.class);

    @Autowired
    private ProductCacheService cacheService;

    /**
     * 查询全部产品
     */
    public List<Product> findAll() {
        return cacheService.readAll();
    }

    /**
     * 查询全部产品
     */
    public Product findOne(String id) {
        Product product = cacheService.findOne(id);
        if (product == null) {
            cacheService.releaseCache(id);
        }
        return product;
    }

    //容器初始化完成后会监听这个时间
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Product> products = findAll();
        products.forEach(product -> cacheService.putCache(product));
    }
}
