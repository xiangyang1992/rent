package com.keith.rent.web.util;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
public class BeanHelper {

    public void copyBeanProperties(Object dist, Object source) {
        if (Map.class.isInstance(dist)) {
            try {
                map2Bean((Map)dist, source);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void map2Bean(Map map, Object bean) throws Exception {
        map2Bean(map, bean, true);
    }

    private void map2Bean(Map map, Object bean, boolean overwrite) throws Exception{
        Set keySet = map.keySet();
        Iterator var6 = keySet.iterator();
        while (var6.hasNext()) {
            Object keyObj = var6.next();
            String key = (String) keyObj;
            Object value = map.get(key);

            Field keyField = bean.getClass().getDeclaredField(key);
            keyField.setAccessible(true);
            if (overwrite) {

            }
        }
    }
}
