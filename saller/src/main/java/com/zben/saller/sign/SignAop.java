package com.zben.saller.sign;

import com.zben.saller.service.SignService;
import com.zben.unit.RSAUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @Author: zben
 * @Description:验签
 * @Date: 15:16 2019/6/16
 */
@Component
@Aspect
public class SignAop {

    @Autowired
    private SignService signService;

    @Before(value = "execution(* com.zben.saller.controller.*.*(..)) && args(authId, sign, text, ..)")
    public void verify(String authId, String sign, SignText text) {
        String publicKey = signService.getPublicKey(authId);
        Assert.isTrue(RSAUtil.verify(text.toText(), sign, publicKey), "验签失败");
    }
}
