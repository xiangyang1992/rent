package com.keith.rent.web.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
public class MapToBeanUtils {

    /**
     * map转换为bean
     *
     * @param cls bean类型
     * @param map map数据
     * @return
     */
    public static <T> T mapToBean(Class<T> cls, Map<String, Object> map) {
        if (StringUtils.isEmpty(map)) {
            return null;
        }
        Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd hh:mm").create();
        //对于javaBean对象直接给出class实例
        return (T) gson.fromJson(toJson(map), cls);
    }


    public static String toJson(Object object) {
        if (StringUtils.isEmpty(object))
            return "";
        Gson g = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd hh:mm").create();
        return g.toJson(object);
    }
}
