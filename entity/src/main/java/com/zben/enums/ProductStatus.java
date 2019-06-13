package com.zben.enums;

/**
 * @Author: zben
 * @Description:
 * @Date: 17:37 2019/6/11
 */
public enum ProductStatus {
    //状态，AUDINING:审核中，IN_SELL：销售中，LOCKED：暂停销售，FINISHED：已结束
    AUDINING("审核中"),
    IN_SELL("销售中"),
    LOCKED("暂停销售"),
    FINISHED("已结束");

    private String desc;

    ProductStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
