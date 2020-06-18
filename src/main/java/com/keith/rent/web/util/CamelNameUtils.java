package com.keith.rent.web.util;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
public class CamelNameUtils {


    public static String camel2underscore(String camelName) {
        camelName = capitalize(camelName);
        String regex = "([A-Z][a-z]+)";
        String replacement = "$1_";

        String underscoreName = camelName.replaceAll(regex, replacement);
        underscoreName = underscoreName.toLowerCase().substring(0, underscoreName.length() - 1);
        return underscoreName;
    }

    private static String capitalize(String str) {
        int strLen;
        if ((str == null) || (strLen = str.length() )== 0) {
            return str;
        }
        return Character.toTitleCase(str.charAt(0)) + str.substring(1);

    }

}
