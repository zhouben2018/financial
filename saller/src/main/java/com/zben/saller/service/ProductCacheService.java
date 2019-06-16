package com.zben.saller.service;

import com.hazelcast.core.HazelcastInstance;
import com.zben.api.ProductRpc;
import com.zben.domain.ProductRpcReq;
import com.zben.entity.Product;
import com.zben.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: zben
 * @Description:
 * @Date: 16:47 2019/6/14
 */
@Service
public class ProductCacheService {

    final static String CACHE_NAME = "product";
    private static Logger LOG = LoggerFactory.getLogger(ProductCacheService.class);

    @Autowired
    private ProductRpc productRpc;

    @Autowired
    private HazelcastInstance hazelcastInstance;

    /**
     * 读取缓存
     */
    @Cacheable(cacheNames = CACHE_NAME)
    public Product findOne(String id) {
        LOG.info("rpc查询单个产品，id:{}", id);
        Product result = productRpc.findOne(id);
        LOG.info("rpc查询单个产品，结果result:{}", result);
        return result;
    }

    /**
     * 加载全部产品缓存
     */
    public List<Product> readAll() {
        Map map = hazelcastInstance.getMap(CACHE_NAME);
        List<Product> products = null;
        if (!CollectionUtils.isEmpty(map)) {
            products = new ArrayList<>();
            products.addAll(map.values());
        } else {
            products = findAll();
        }
        return products;
    }


    /**
     * 查询全部产品
     */
    public List<Product> findAll() {
        ProductRpcReq req = new ProductRpcReq();
        List<String> statusList = new ArrayList<>();
        statusList.add(ProductStatus.IN_SELL.name());
        req.setStatusList(statusList);
        LOG.info("rpc查询全部产品，请求req:{}", req);
        List<Product> result = productRpc.query(req);
        LOG.info("rpc查询全部产品，结果result:{}", result);
        return result;
    }

    /**
     * 更新缓存
     */
    @CachePut(cacheNames = CACHE_NAME, key = "#product.id")
    public Product putCache(Product product) {
        return product;
    }

    /**
     * 删除缓存
     */
    @CacheEvict(cacheNames = CACHE_NAME)
    public void releaseCache(String id) {

    }
}
