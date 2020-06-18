package com.keith.rent.core.utils;

import java.security.MessageDigest;

/**
 * MD5 工具类
 */
public class PasswordEncoder {
    private static final String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f"};
    private static final String MD5 = "MD5";
    private static final String SHA = "SHA";

    //盐
    private Object salt;
    //算法规则
    private String algorithm;

    public PasswordEncoder(Object salt) {
        this(salt, MD5);
    }

    public PasswordEncoder(Object salt, String algorithm) {
        this.salt = salt;
        this.algorithm = algorithm;
    }

    public String encode(String rawPass) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            //加密之后的字符串
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(rawPass).getBytes("utf-8")));
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b
     *            字节数组
     * @return 16进制字串
     */
    private String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (byte b1 : b) {
            sb.append(bytetToHexString(b1));
        }
        return sb.toString();
    }

    private static String bytetToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private String mergePasswordAndSalt(String password) {
        if (password == null) {
            password = "";
        }
        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }

    /**
     * 密码匹配验证
     *
     * @param encPass 明文
     * @param rawPass 密文
     * @return
     */
    public Boolean matches(String encPass, String rawPass) {
        String passwd1 = "" + encPass;
        String passwd2 = encode(rawPass);
        return passwd1.equals(passwd2);
    }


    public static void main(String[] args) {
        String salt = "123456";
        PasswordEncoder encoder = new PasswordEncoder(salt, MD5);
        String code = encoder.encode("123456");
        System.out.println(code);
    }

}
