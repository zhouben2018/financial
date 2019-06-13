package com.zben.api;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.zben.domain.ProductRpcReq;
import com.zben.entity.Product;

import java.util.List;

/**
 * @Author: zben
 * @Description:产品相关rpc服务
 * @Date: 10:49 2019/6/13
 */
@JsonRpcService("rpc/products")
public interface ProductRpc {

    /**
     * 搜索产品
     */
    List<Product> query(ProductRpcReq req);

    /**
     * 查询单个产品
     */
    Product findOne(String id);
}
