package com.zben.saller.service;

import com.google.common.collect.Lists;
import com.zben.api.ProductRpc;
import com.zben.domain.ProductRpcReq;
import com.zben.entity.Product;
import com.zben.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zben
 * @Description:
 * @Date: 11:12 2019/6/13
 */
@Service
public class ProductRpcService {

    private static Logger LOG = LoggerFactory.getLogger(ProductRpcService.class);

    @Autowired
    private ProductRpc productRpc;

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
     * 查询全部产品
     */
    public Product findOne(String id) {
        LOG.info("rpc查询单个产品，id:{}", id);
        Product result = productRpc.findOne(id);
        LOG.info("rpc查询单个产品，结果result:{}", result);
        return result;
    }

    @PostConstruct
    public void testFindAll() {
        findAll();
    }
}
