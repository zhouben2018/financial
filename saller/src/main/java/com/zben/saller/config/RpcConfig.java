package com.zben.saller.config;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import com.zben.api.ProductRpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: zben
 * @Description:
 * @Date: 11:20 2019/6/13
 */
@Configuration
@ComponentScan(basePackageClasses = {ProductRpc.class})
public class RpcConfig {

    private static Logger LOG = LoggerFactory.getLogger(RpcConfig.class);

    @Bean
    public AutoJsonRpcClientProxyCreator rpcClientProxyCreator(@Value("${rpc.manager.url}") String url) {
        AutoJsonRpcClientProxyCreator creator = new AutoJsonRpcClientProxyCreator();
        try {
            creator.setBaseUrl(new URL(url));
        } catch (MalformedURLException e) {
            LOG.error("创建rpc服务地址错误", e);
            e.printStackTrace();
        }
        //扫描rpc扫描包
        creator.setScanPackage(ProductRpc.class.getPackage().getName());
        return creator;
    }
}
