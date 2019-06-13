package com.zben;

import com.zben.swagger.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @Author: zben
 * @Description:管理启动类
 * @Date: 09:36 2019/6/12
 */
@SpringBootApplication
@Import(SwaggerConfig.class)
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
