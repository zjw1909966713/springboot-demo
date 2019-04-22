package com.highrock.util;

import java.util.UUID;

/**
 * 创建token工具
 */
public class TokenUtil {


    /**
     * 创建token工具
     *
     * @return
     */
    public static String createToken() {
        String token = UUID.randomUUID().toString().replace("-", "");
        return token;
    }


    public static void main(String[] args) {
        //TokenUtil.createToken();
    }
}
