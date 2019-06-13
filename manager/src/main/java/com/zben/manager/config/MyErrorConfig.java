package com.zben.manager.config;

import com.zben.manager.error.MyErrorController;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author: zben
 * @Description:
 * @Date: 16:16 2019/6/12
 */
@Configuration
public class MyErrorConfig {

    @Bean
    public MyErrorController basicErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties,
                                                  ObjectProvider<List<ErrorViewResolver>> errorViewResolvers) {
        return new MyErrorController(errorAttributes, serverProperties.getError(),
                errorViewResolvers.getIfAvailable());
    }

}
