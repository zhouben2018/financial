package com.zben.saller;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @Author: zben
 * @Description:
 * @Date: 15:22 2019/6/14
 */
@Component
public class HazelCastTest {

    @Autowired
    private HazelcastInstance hazelcastInstance;
/*
    @PostConstruct
    public void put() {
        Map map = hazelcastInstance.getMap("zben");
        map.put("name", "zben");
    }*/
}
