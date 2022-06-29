package com.yyd.yyd.utils;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class AESUtils {


    /**
     * 加解密密钥, 外部可以
     */
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    // appKey,每隔一段时间进行替换即可
    // 可以设计成保存到数据库中或者那里，然后进行每隔一段时间进行替换，增加保密的安全性

  //  private static final String appKey = "caliper";

    /**
     * 30      * AES 加密操作

     */
    public static String encrypt(String content,String appKey) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(appKey));// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return Base64.encodeBase64String(result);// 通过Base64转码返回
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 56      * AES 解密操作
     * 57      *
     * 58      * @param content
     * 59      *            待解密内容
     * 60      * @param appKey
     * 61      *            加密appKey
     * 62      * @return
     * 63
     */
    public static String decrypt(String content,String appKey) {

        try {
            // 实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            // 使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(appKey));

            // 执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));

            return new String(result, "utf-8");
        } catch (Exception ex) {
          //  Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * 85      * 生成加密秘钥
     * 86      *
     * 87      * @return
     * 88
     */
    private static SecretKeySpec getSecretKey(String appKey) {
        // 返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            // SecureRandom 实现随操作系统本身的內部状态，除非调用方在调用 getInstance 方法之后又调用了 setSeed 方法；该实现在
            // windows 上每次生成的 key 都相同，但是在 solaris 或部分 linux 系统上则不同。解决在linux操作系统中加密产生的字符串不一致问题。
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

            secureRandom.setSeed(appKey.getBytes());

            // AES 要求密钥长度为 128
            kg.init(128, secureRandom);

            // 生成一个密钥
            SecretKey secretKey = kg.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
