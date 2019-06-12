package com.zben.manager.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: zben
 * @Description:
 * @Date: 17:26 2019/6/11
 */
@Entity(name = "order_t")
public class Order {

    @Id
    private String orderId;

    //渠道编号
    private String chanId;

    private String productId;

    //渠道用户编号
    private String chanUserId;

    //类型，APPLY：申购，REDEEM：赎回
    private String orderType;

    //状态，INIT：初始化，PROCESS：处理中，SUCCESS：成功，FAIL：失败
    private String orderStatus;

    //外部订单编号
    private String outerOrderId;

    //金额
    private BigDecimal amount;

    private String memo;

    private Date createAt;

    private Date updateAt;

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", chanId='" + chanId + '\'' +
                ", productId='" + productId + '\'' +
                ", chanUserId='" + chanUserId + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", outerOrderId='" + outerOrderId + '\'' +
                ", amount=" + amount +
                ", memo='" + memo + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getChanId() {
        return chanId;
    }

    public void setChanId(String chanId) {
        this.chanId = chanId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getChanUserId() {
        return chanUserId;
    }

    public void setChanUserId(String chanUserId) {
        this.chanUserId = chanUserId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOuterOrderId() {
        return outerOrderId;
    }

    public void setOuterOrderId(String outerOrderId) {
        this.outerOrderId = outerOrderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
