package com.yyd.yyd.frame.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class SecurityHelper {

//    @Value("${caliper.security.aes.key}")
    private String aesKey;

    public String decrypt(String text) {
        if (StringUtils.isBlank(text)) {
            return text;
        }

        MyAESHelper aesHelper = new MyAESHelper("1:U1Iq8P9Hf3SV66aQ6s0hBz5yrt66c4CT");
        return aesHelper.decrypt(text);
    }

    public String encrypt(String text) {
        if (StringUtils.isBlank(text)) {
            return text;
        }

        MyAESHelper aesHelper = new MyAESHelper("1:U1Iq8P9Hf3SV66aQ6s0hBz5yrt66c4CT");
        return aesHelper.encrypt(text);
    }
}
