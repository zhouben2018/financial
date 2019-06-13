package com.zben.manager.rpc;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.zben.api.ProductRpc;
import com.zben.domain.ProductRpcReq;
import com.zben.entity.Product;
import com.zben.manager.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zben
 * @Description:产品rpc服务实现类
 * @Date: 10:58 2019/6/13
 */
@Service
@AutoJsonRpcServiceImpl
public class ProductRpcImpl implements ProductRpc{

    private static Logger LOG = LoggerFactory.getLogger(ProductRpcImpl.class);

    @Autowired
    private ProductService productService;

    @Override
    public List<Product> query(ProductRpcReq req) {
        LOG.info("搜索产品，入参req:{}",req);
        Pageable pageable = new PageRequest(0, 100, Sort.Direction.DESC, "rewardRate");
        Page<Product> result = productService.query(req.getIdList(), req.getMinRewardRate(), req.getMaxRewardRate(),
                req.getStatusList(), pageable);
        LOG.info("搜索产品，结果:{}",result);
        return result.getContent();
    }

    @Override
    public Product findOne(String id) {
        LOG.info("搜索产品，入参id:{}",id);
        Product result = productService.findOne(id);
        LOG.info("搜索产品，入参result:{}",result);
        return result;
    }
}
