package com.zben.saller.params;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zben.saller.sign.SignText;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: zben
 * @Description:下单请求参数
 * @Date: 15:12 2019/6/16
 */
public class OrderParam implements SignText {

    //渠道编号
    private String chanId;

    private String productId;

    //渠道用户编号
    private String chanUserId;

    //外部订单编号
    private String outerOrderId;

    //金额
    private BigDecimal amount;

    private String memo;

    @Override
    public String toString() {
        return "OrderParam{" +
                "chanId='" + chanId + '\'' +
                ", productId='" + productId + '\'' +
                ", chanUserId='" + chanUserId + '\'' +
                ", outerOrderId='" + outerOrderId + '\'' +
                ", amount=" + amount +
                ", memo='" + memo + '\'' +
                '}';
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

}
