package com.deepexi.promotion.common;

import java.security.SecureRandom;

/**
 * @author zhoust
 * @date 2019/5/30
 **/
public class RandomGenerator {
    public static final String SYMBOLS = "0123456789";

    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * 获取长度为a的随机数字字符串
     * @param a
     * @return
     */
    public static String getNonce_str(int a) {

        char[] chars = new char[a];

        for (int index = 0; index < chars.length; ++index) {
            chars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }

        return new String(chars);
    }
}
