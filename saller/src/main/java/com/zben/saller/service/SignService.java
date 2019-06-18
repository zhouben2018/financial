package com.zben.saller.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zben
 * @Description:
 * @Date: 15:20 2019/6/16
 */
@Service
public class SignService {

    static Map<String, String> PUBLIC_KEY = new HashMap<>();
    static {
        PUBLIC_KEY.put("1000", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQhrXMsiRp6P/aHeaEEQbxsoyE\n" +
                "m1q+n7GE6X5Jg2tHT22K4r4BfxkUjUzvDDfg2dIy4gEkmPjKkO3dxaTlGlLPn5Dd\n" +
                "N1Me/9pedHOz+jKTqZAv/L05XwUi8LdmHP0gG92C3kx6mylKBpPWKL7m5n8ZWLnc\n" +
                "nTT0mRG/4xyLxWWnnQIDAQAB");
    }

    /**
     * 根据授权编号获取公钥
     * @param authId
     * @return
     */
    public String getPublicKey(String authId) {
        return PUBLIC_KEY.get(authId);
    }
}
