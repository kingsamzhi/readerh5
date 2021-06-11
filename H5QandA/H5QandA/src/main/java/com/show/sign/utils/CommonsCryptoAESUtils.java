package com.show.sign.utils;



import org.apache.commons.codec.binary.Base64;
import org.apache.commons.crypto.stream.CryptoInputStream;
import org.apache.commons.crypto.stream.CryptoOutputStream;
import org.apache.commons.lang3.time.FastDateFormat;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * AES加密解密（基于Apache Commons Crypto封装）
 *
 * @author roy mustang
 */
public class CommonsCryptoAESUtils {

    // 加密方式
    private static final String TRANSFORM = "AES/CBC/PKCS5Padding";

    // 默认向量
    private static final String DEFAULT_IV = "0000000000000000";

    /**
     * 加密
     *
     * @param data
     * @param key
     *
     * @return
     * @throws IOException
     */
    public static String encrypt(String data, String key) throws IOException {
        return encrypt(data, key, DEFAULT_IV);
    }

    /**
     * 加密
     *
     * @param data
     * @param key
     * @param iv
     *
     * @return
     * @throws IOException
     */
    public static String encrypt(String data, String key, String iv) throws IOException {
        // 16位秘钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(getUTF8Bytes(key), "AES");
        // 向量
        final IvParameterSpec ivParameterSpec = new IvParameterSpec(getUTF8Bytes(iv));
        Properties properties = new Properties();

        // 将明文以流的方式加密
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (CryptoOutputStream cos = new CryptoOutputStream(TRANSFORM, properties, outputStream, secretKeySpec, ivParameterSpec)) {
            cos.write(getUTF8Bytes(data));
            cos.flush();
        }

        // 密文，URL安全的Base64转码
        String cipherStr = Base64.encodeBase64URLSafeString(outputStream.toByteArray());
        return cipherStr;
    }

    /**
     * 解密
     *
     * @param data
     * @param key
     *
     * @return
     * @throws IOException
     */
    public static String decrypt(String data, String key) throws IOException {
        return decrypt(data, key, DEFAULT_IV);
    }

    /**
     * 解密
     *
     * @param data
     * @param key
     * @param iv
     *
     * @return
     * @throws IOException
     */
    public static String decrypt(String data, String key, String iv) throws IOException {
        // 16位秘钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(getUTF8Bytes(key), "AES");
        // 向量
        final IvParameterSpec ivParameterSpec = new IvParameterSpec(getUTF8Bytes(iv));
        Properties properties = new Properties();

        // 将密文已流的方式解密
        InputStream inputStream = new ByteArrayInputStream(Base64.decodeBase64(data));
        try (CryptoInputStream cis = new CryptoInputStream(TRANSFORM, properties, inputStream, secretKeySpec, ivParameterSpec)) {
            byte[] decryptedData = new byte[1024];
            int decryptedLen = 0;
            int i;
            while ((i = cis.read(decryptedData, decryptedLen, decryptedData.length - decryptedLen)) > -1) {
                decryptedLen += i;
            }
            return new String(decryptedData, 0, decryptedLen, StandardCharsets.UTF_8);
        }
    }

    /**
     * UTF-8编码
     *
     * @param input
     *
     * @return
     */
    private static byte[] getUTF8Bytes(String input) {
        return input.getBytes(StandardCharsets.UTF_8);
    }


}
