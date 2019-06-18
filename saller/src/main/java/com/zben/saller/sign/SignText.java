package com.zben.saller.sign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zben.unit.JsonUtil;

/**
 * @Author: zben
 * @Description:签名明文
 * @Date: 15:09 2019/6/16
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public interface SignText {

    default String toText() {
        return JsonUtil.toJson(this);
    }
}
