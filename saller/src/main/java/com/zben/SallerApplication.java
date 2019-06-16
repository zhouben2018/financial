package com.zben;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author: zben
 * @Description:
 * @Date: 11:30 2019/6/13
 */
@SpringBootApplication
@EnableCaching  //开启缓存
public class SallerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SallerApplication.class, args);
    }

}
