package com.zben.saller.service;

import com.zben.entity.Order;
import com.zben.entity.Product;
import com.zben.enums.OrderStatusEnums;
import com.zben.enums.OrderTypeEnums;
import com.zben.saller.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: zben
 * @Description:
 * @Date: 13:21 2019/6/16
 */
@Service
public class OrderService {

    private static Logger LOG = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private ProductRpcService productRpcService;

    @Autowired
    private OrderRepository orderRepository;

    public Order apply(Order order) {
        //校验参数
        checkOrder(order);
        //设置默认值
        setDefault(order);
        //下订单
        Product product = productRpcService.findOne(order.getProductId());
        if (product == null) {
            LOG.info("下单的产品不存在");
            return null;
        }
        return orderRepository.saveAndFlush(order);
    }

    /**
     * 设置默认值
     */
    private void setDefault(Order order) {
        order.setOrderId(UUID.randomUUID().toString().replaceAll("-", ""));
        order.setOrderStatus(OrderStatusEnums.SUCCESS.name());
        order.setOrderType(OrderTypeEnums.APPLY.name());
        order.setCreateAt(new Date());
    }

    /**
     * 校验参数
     */
    private void checkOrder(Order order) {
        Assert.notNull(order, "订单不为空");
        Assert.notNull(order.getChanId(), "渠道编号不为空");
        Assert.notNull(order.getChanUserId(), "渠道用户不为空");
        Assert.notNull(order.getOuterOrderId(), "外部订单编号不为空");
        Assert.notNull(order.getProductId(), "产品id不为空");
        Assert.isTrue(order.getAmount().compareTo(BigDecimal.ZERO) > 0, "金额大于0");
    }
}
