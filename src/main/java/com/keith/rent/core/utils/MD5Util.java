package com.keith.rent.core.utils;

import com.google.common.base.Joiner;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * description:MD5加密工具
 * author:xiangyang
 */
public class MD5Util {

    public static String encrypt(String source)throws UnsupportedEncodingException {
        if (source == null) {
            source = "";
        }
        String result = "";
        try {
            result = encrypt(source, "MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static String encrypt(String source, String algorithm)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] resByteArray = encrypt(source.getBytes("UTF-8"), algorithm);
        return toHexString(resByteArray);
    }

    private static String toHexString(byte[] res) {
        StringBuffer sb = new StringBuffer(res.length >> 1);
        for (int i = 0; i < res.length; i++) {
            String digit = Integer.toHexString(0xFF & res[i]);
            if (digit.length() == 1) {
                digit = '0' + digit;
            }
            sb.append(digit);
        }
        return sb.toString().toUpperCase();
    }


    private static byte[] encrypt(byte[] source, String algorithm)
    throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.reset();
        md.update(source);
        return md.digest();
    }


    public static String encrypt(String [] source,String flag)throws UnsupportedEncodingException {
        if (flag == null) {
            flag = "";
        }
        if (source == null) {
            return encrypt("");
        }
        String str = Joiner.on(flag).join(source);
        return encrypt(str);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(MD5Util.encrypt("123456"));
    }
}
