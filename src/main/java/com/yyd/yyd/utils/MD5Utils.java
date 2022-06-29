package com.yyd.yyd.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class MD5Utils {

    private final static String[] strDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private final static String SALT = "qnXF468OdZbU054abyBoTdYQLn9Woz28";

    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String md5Code(String str) {
        String resultString = null;
        try {
            resultString = new String(str);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteToString(md.digest(str.getBytes()));

        } catch (NoSuchAlgorithmException ex) {
            log.error("md5Code | error | {}", str, ex);
        }
        return resultString;
    }


    public static String md5SaltCode(String str) {
        String resultString = null;
        try {
            String finalStr = str + SALT;
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteToString(md.digest(finalStr.getBytes()));

        } catch (NoSuchAlgorithmException ex) {
            log.error("md5Code | error | {}", str, ex);
        }
        return resultString;
    }

    public static String md5SaltUpperCase(String str) {
        return md5SaltCode(str).toUpperCase();
    }

}
