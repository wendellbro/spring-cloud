package com.business.gateway.utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 安全工具类，包含授权签名的hmac_sha1算法，主要用于计算签名?
 */
public final class SecurityUtil {

    public static final String HMAC_SHA1 = "HmacSHA1";

    /**
     * HMAC see：http://www.ietf.org/rfc/rfc2104.txt
     */
    public static byte[] hmacSha1(byte[] data, byte[] key, int offset, int len) {
        SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
        Mac mac = null;
        try {
            mac = Mac.getInstance(HMAC_SHA1);
            mac.init(signingKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        mac.update(data, offset, len);
        return mac.doFinal();
    }
    
    public static byte[] hmacSha1(byte[][] datas, byte[] key) {
        SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
        Mac mac = null;
        try {
            mac = Mac.getInstance(HMAC_SHA1);
            mac.init(signingKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        for (byte[] data : datas) {
            mac.update(data);
        }
        return mac.doFinal();
    }

    public static byte[] hmacSha1(String[] datas, byte[] key) {
        SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
        Mac mac = null;
        try {
            mac = Mac.getInstance(HMAC_SHA1);
            mac.init(signingKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        for (String data : datas) {
			mac.update(data.getBytes(StandardCharsets.UTF_8));
		}
        return mac.doFinal();
    }

    public static String hmacSha1ToHexStr(byte[] data, byte[] key, int offset, int len) {
        byte[] rawHmac = hmacSha1(data, key, offset, len);
        return encodeHexStr(rawHmac);
    }
    
    public static String hmacSha1ToHexStr(byte[] data, String key, int offset, int len) {
        return hmacSha1ToHexStr(data, key.getBytes(StandardCharsets.UTF_8), offset, len);
    }
    
    public static String hmacSha1ToHexStr(String str, String key) {
        byte[] data = str.getBytes(StandardCharsets.UTF_8);
		return hmacSha1ToHexStr(data, key.getBytes(StandardCharsets.UTF_8), 0, data.length);
    }
    
    public static final char[] digital = "0123456789ABCDEF".toCharArray();
    public static String encodeHexStr(final byte[] bytes){
        if(bytes == null){
            return null;
        }
        char[] result = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            result[i*2] = digital[(bytes[i] & 0xf0) >> 4];
            result[i*2 + 1] = digital[bytes[i] & 0x0f];
        }
        return new String(result);
    }

    private SecurityUtil() {
    }
}
