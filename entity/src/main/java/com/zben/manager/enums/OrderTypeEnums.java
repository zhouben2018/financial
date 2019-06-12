package com.zben.manager.enums;

/**
 * @Author: zben
 * @Description:
 * @Date: 17:35 2019/6/11
 */
public enum OrderTypeEnums {
    //类型，APPLY：申购，REDEEM：赎回
    APPLY("申购"),
    REDEEM("赎回");

    private String desc;

    OrderTypeEnums(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
