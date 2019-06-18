package com.zben.saller.controller;

import com.zben.entity.Order;
import com.zben.saller.params.OrderParam;
import com.zben.saller.service.OrderService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zben
 * @Description:
 * @Date: 13:32 2019/6/16
 */
@RestController
@RequestMapping("/order")
@Api(description = "销售端")
public class OrderController {

    private static Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public Order apply(@RequestHeader String authId, @RequestHeader String sign, @RequestBody OrderParam orderParam) {
        LOG.info("下订单  入参order:{}", orderParam);
        Order order = new Order();
        BeanUtils.copyProperties(orderParam, order);
        Order result = orderService.apply(order);
        LOG.info("下订单  结果result:{}", result);
        return result;
    }
}
