package com.yyd.yyd.utils;

import java.util.Base64;

public class Base64Utils {

    public static String decodeStr(final String encode) {
        return new String(Base64.getDecoder().decode(encode));
    }

    public static String encodeStr(final String code) {
        return new String(Base64.getEncoder().encode(code.getBytes()));
    }

    public static String decodeByte(final byte[] encode) {
        return new String(Base64.getDecoder().decode(encode));
    }

    public static String encodeByte(final byte[] bytes) {
        return new String(Base64.getEncoder().encode(bytes));
    }
}
