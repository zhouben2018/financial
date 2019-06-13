package com.zben.manager.config;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zben
 * @Description:rpc相关配置
 * @Date: 11:07 2019/6/13
 */
@Configuration
public class RpcConfig {

    @Bean
    public AutoJsonRpcServiceImplExporter rpcServiceImplExporter() {
        return new AutoJsonRpcServiceImplExporter();
    }
}
