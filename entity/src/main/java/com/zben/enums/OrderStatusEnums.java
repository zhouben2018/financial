package com.zben.enums;

/**
 * @Author: zben
 * @Description:
 * @Date: 17:33 2019/6/11
 */
public enum OrderStatusEnums {

    INIT("初始化"),
    PROCESS("处理中"),
    SUCCESS("成功"),
    FAIL("失败");

    private String desc;

    OrderStatusEnums(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
