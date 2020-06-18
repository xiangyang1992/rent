package com.keith.rent.core.utils;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

public class StringUtil {

    public static boolean isNull(Object v) {
        return (v == null) || ("".equals(v.toString().trim()));
    }

    public static boolean isNotNull(Object v) {
        return !isNull(v);
    }

    public static String trimNull(Object v) {
        return v == null ? "" : v.toString().trim();
    }

    public static String getRandom(int length) {

        String allChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return sb.toString();
    }

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getFormatNum(int currNum, String numFormat) {
        DecimalFormat df = new DecimalFormat(numFormat);
        return df.format(currNum);
    }

    public static String getFormatNum(long currNum, String numFormat) {
        DecimalFormat df = new DecimalFormat(numFormat);
        return df.format(currNum);
    }

}
