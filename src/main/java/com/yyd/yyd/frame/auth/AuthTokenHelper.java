package com.yyd.yyd.frame.auth;


import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.security.MessageDigest;

public class AuthTokenHelper {
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenHelper.class);

    private String accessKey;
    private String secretKey;

    private static final String TOKEN_VERSION = "v3";
    private static final String UTF8_ENC = "UTF-8";

    public AuthTokenHelper() {

    }

    public AuthTokenHelper(String ak, String sk) {
        accessKey = ak;
        secretKey = sk;
    }

    /**
     * generate the token according the request or response contents
     *
     * @return the token
     */
    public String generateToken() {
        int expireTime = 600 + (int) (System.currentTimeMillis() / 1000);

        return generateToken(expireTime);
    }

    public boolean verifyToken(String token) {
        logger.info("verifyToken | entry | {}", token);

        if (StringUtils.isEmpty(token)) {
            logger.error("verifyToken | token empty");
            return false;
        }

        try {
            String[] tokenParts = token.split("-");

            if (tokenParts.length != 4) {
                logger.error("verifyToken | invalid token format | {}", token);
                return false;
            }

            if (!TOKEN_VERSION.equals(tokenParts[0])) {
                logger.error("verifyToken | invalid token protocol version | {}", token);
                return false;
            }

            int expireTime = Integer.parseInt(tokenParts[2]);
            if (expireTime < System.currentTimeMillis() / 1000) {
                logger.error("verifyToken | expired token | {}", token);
                return false;
            }

            if (token.equals(generateToken(expireTime))) {
                return true;
            }
        } catch (Exception e) {
            logger.error("verifyToken | failed to parse token | {}", token, e);
        }

        return false;
    }

    private String generateToken(int expireTime) {
        logger.info("generateToken | entry | {}", expireTime);

        if (StringUtils.isEmpty(accessKey) || StringUtils.isEmpty(secretKey)) {
            logger.error("generateToken | invalid AK or SK | {}", expireTime);

            throw new RuntimeException("Invalid AK or SK");
        }

        String token = "";
        try {
            String signPrefix = String.format("%s-%s-%d", TOKEN_VERSION,
                    accessKey, expireTime);

            String sign = String.format("%s-%s-%s", secretKey,
                    signPrefix, secretKey);

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(sign.getBytes(Charset.forName(UTF8_ENC)));

            //  v2-{AK}-{ExpireTime}-{Signature}
            token = String.format("%s-%s", signPrefix, new String(Hex.encodeHex(md5.digest())));
            logger.info("generateToken: {}", token);
        } catch (Exception e) {
            logger.error("generateToken | error | {} | {}", expireTime, token, e);
        }

        return token;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
