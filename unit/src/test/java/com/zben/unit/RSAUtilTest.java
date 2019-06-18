package com.zben.unit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class RSAUtilTest {

    static final String publickKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQhrXMsiRp6P/aHeaEEQbxsoyE\n" +
            "m1q+n7GE6X5Jg2tHT22K4r4BfxkUjUzvDDfg2dIy4gEkmPjKkO3dxaTlGlLPn5Dd\n" +
            "N1Me/9pedHOz+jKTqZAv/L05XwUi8LdmHP0gG92C3kx6mylKBpPWKL7m5n8ZWLnc\n" +
            "nTT0mRG/4xyLxWWnnQIDAQAB";

    static final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANCGtcyyJGno/9od\n" +
            "5oQRBvGyjISbWr6fsYTpfkmDa0dPbYrivgF/GRSNTO8MN+DZ0jLiASSY+MqQ7d3F\n" +
            "pOUaUs+fkN03Ux7/2l50c7P6MpOpkC/8vTlfBSLwt2Yc/SAb3YLeTHqbKUoGk9Yo\n" +
            "vubmfxlYudydNPSZEb/jHIvFZaedAgMBAAECgYBcT4Yje6kLCY5ZrGrjg/YjAPub\n" +
            "qHpfcKpRQFwbhwXyp4LCvOWIHA+qPXMVT79c1pBq3gnLfbyFT+s9BcWddi2510KW\n" +
            "1mPFKvdzdF+LEKRt3J0DDySoz20gMXH9rmf6MX+jHsa7tlc5i+/JsFtN9gfMdK5b\n" +
            "An9bALhsNkIxqV5nsQJBAOlfiuvOxcYBPJFzseaynTV4rqJmH3k0w0RtRgA/YCnq\n" +
            "7FZH/EMrJHBRYH7innaDjjgFJlIU6+sHgFgkqhq8+j8CQQDkvnNbw+uJt4t1NC4f\n" +
            "pWOvQeX6zBJX+oUrcNpCLOQKJPMXOzcbgVeSt9/L6X+Xx9k7paS+c2Oi5EMDclvC\n" +
            "Kk8jAkAW/x03Y2bFvjVzt8zeCNfI/x1mT13mmbZsNcBiIdyeBNJJJkrLa6LplntF\n" +
            "xZsL3qbTCl7eU8VzoYeaJ/LDM8ANAkBrqXAmWtp7oVYUPhEPeAqpLH0QRAjGO/V9\n" +
            "8snnp7VVSPrZjldrrL8JIV5GXYpeaAPI3TfjI/BbdNBNyP+DLeK9AkEAgkY/Ua0K\n" +
            "ugN9EcrEwQb0Q2pj4lv48gy/LNhHF+nCfIrNNiEqdzRihSMWBb5bkV9PX4WQK9mZ\n" +
            "2P3u6RGeftiGKg==";

    @Test
    public void test() {
        String text = "{\"amount\":10,\"chanId\":\"111\",\"chanUserId\":\"112\",\"memo\":\"\",\"outerOrderId\":\"xxx\",\"productId\":\"T001\"}";
        String sign = RSAUtil.sign(text, privateKey);
        System.out.println(sign);
        boolean verify = RSAUtil.verify(text, sign, publickKey);
        System.out.println(verify);
    }
}
