package com.yyd.yyd.frame.security;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * AES ECB cipher helper
 *
 * @since 2016-10-22
 */
public class MyAESHelper {
    private static final Logger logger = LoggerFactory.getLogger(MyAESHelper.class);
    private static final String CHARSET = "UTF-8";

    private static final int IV_LENGTH = 16;

    private String keyType;

    private String keyValue;

    private String fullKey;

    public MyAESHelper(String key) {
        String[] keyValuePair = key.split(":");
        keyType = keyValuePair[0];
        keyValue = keyValuePair[1];
        fullKey = key;

        if (keyValue.length() < IV_LENGTH) {
            throw new IllegalArgumentException("bad encrypted key for secure file and must be greater than " + IV_LENGTH);
        }
    }

    public String encrypt(final String content) {
        return this.encrypt(getIvEncKey(), content);
    }

    private String encrypt(final String varEncKey, final String content) {
        try {
            final Cipher cipher = getCipher();
            byte[] aesKeys = getAesCipherKey(keyValue, varEncKey);
            init(cipher, Cipher.ENCRYPT_MODE, aesKeys);
            ByteGroup byteGroup = new ByteGroup();
            byteGroup.addBytes(content.getBytes("UTF-8"));
            byte[] padBytes = PKCS7Encoder.encode(byteGroup.size());
            byteGroup.addBytes(padBytes);
            byte[] encrypted = cipher.doFinal(byteGroup.toBytes());
            return String.format("%s:%s", keyType, Base64.encodeBase64String(encrypted));
        } catch (Exception e) {
            logger.error("encrypt: failed to encrypt secure filed.error message={}", e.getMessage());
            throw new IllegalStateException("failed to encrypt the secure field.");
        }
    }

    public String decrypt(final String content) {
        return decrypt(getIvEncKey(), content);
    }

    private String decrypt(final String varEncKey, String content) {
        try {
            String[] st = StringUtils.split(content, ":", 2);
            if (st.length == 2) {
                content = st[1];
            }
            final Cipher cipher = getCipher();
            byte[] aesKeys = getAesCipherKey(keyValue, varEncKey);
            init(cipher, Cipher.DECRYPT_MODE, aesKeys);
            byte[] decrypted = cipher.doFinal(Base64.decodeBase64(content));
            decrypted = PKCS7Encoder.decode(decrypted);
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            logger.error("decrypt: failed to decrypt the secure field. error message={}", e.getMessage());
            throw new IllegalStateException("failed to decrypt the secure field.");
        }
    }

    private String getIvEncKey() {
        return getMd5Key(fullKey);
    }

    private static void init(Cipher cipher, int mode, byte[] aesKeys) throws InvalidKeyException {
        cipher.init(mode, new SecretKeySpec(aesKeys, "AES"));
    }

    private static Cipher getCipher() {
        try {
            return Cipher.getInstance("AES/ECB/NoPadding");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static byte[] getAesCipherKey(final String fixedEncKey, final String varEncKey) {
        return getMd5Digest(fixedEncKey + varEncKey);
    }

    private static byte[] getMd5Digest(final String key) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(key.getBytes());
            return Hex.encodeHexString(digest.digest()).substring(0, IV_LENGTH).getBytes("UTF-8");
        } catch (Exception e) {
            logger.error("failed to get key because of NoSuchAlgorithmException,e.msg={}", e.getMessage());
            throw new IllegalStateException("NoSuchAlgorithmException,stop!");
        }
    }

    public static String getMd5Key(String content) {
        return getMd5Key(content, ReturnType.HEX);
    }

    public static String getMd5Key(String content, ReturnType retType) {
        return getMsgDigest(content, retType, "MD5");
    }

    private static String getMsgDigest(String content, ReturnType retType, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.reset();
            digest.update(content.getBytes(CHARSET));
            return retType.toString(digest.digest());
        } catch (Exception e) {
            throw new IllegalStateException(algorithm + " message digest fail,e.msg=" + e.getMessage());
        }
    }

    public enum ReturnType {
        B64 {
            @Override
            public String toString(byte[] bs) {
                return java.util.Base64.getEncoder().encodeToString(bs);
            }
        }, HEX {
            @Override
            public String toString(byte[] bs) {
                return Hex.encodeHexString(bs);
            }
        };

        public abstract String toString(byte[] bs);
    }

    private static class PKCS7Encoder {

        private static int BLOCK_SIZE = 32;

        public static byte[] encode(int count) throws UnsupportedEncodingException {
            int amountToPad = BLOCK_SIZE - (count % BLOCK_SIZE);
            if (amountToPad == 0) {
                amountToPad = BLOCK_SIZE;
            }
            char padChr = (char) (amountToPad & 0xFF);
            String tmp = new String();
            for (int index = 0; index < amountToPad; index++) {
                tmp += padChr;
            }
            return tmp.getBytes(CHARSET);
        }

        public static byte[] decode(byte[] decrypted) {
            int pad = (int) decrypted[decrypted.length - 1];
            if (pad < 1 || pad > 32) {
                pad = 0;
            }
            return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
        }
    }


    private static class ByteGroup {
        private final List<Byte> byteContainer = new ArrayList<Byte>();

        public byte[] toBytes() {
            byte[] bytes = new byte[byteContainer.size()];
            for (int i = 0; i < byteContainer.size(); i++) {
                bytes[i] = byteContainer.get(i);
            }
            return bytes;
        }

        public ByteGroup addBytes(byte[] bytes) {
            for (byte b : bytes) {
                byteContainer.add(b);
            }
            return this;
        }

        public int size() {
            return byteContainer.size();
        }
    }

    public static void main(String[] args) {
        MyAESHelper helper = new MyAESHelper("1:U1Iq8P9Hf3SV66aQ6s0hBz5yrt66c4CT");
     //   System.out.println(helper.encrypt("985336171717100988"));
       // SecurityHelper securityHelper = new SecurityHelper();
        String q = helper.encrypt("12345678912");
        System.out.println(q);
        String a = helper.encrypt("44444444444");
        System.out.println(a);
        String z = helper.encrypt("55555555555");
        System.out.println(z);
        String s = helper.encrypt("44444444444");
        System.out.println(s);
    }

}
