package com.keith.rent.web.util;


import com.keith.rent.core.utils.DateUtil;
import com.keith.rent.core.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;


public class EntityHelper {

    private static final Logger log = LoggerFactory.getLogger(EntityHelper.class);

    public static boolean injectPropFromRequest(Object bean, HttpServletRequest request) {
        try {
            PropertyDescriptor[] pds = Introspector
                    .getBeanInfo(bean.getClass()).getPropertyDescriptors();
            String propName = null;
            String value = null;
            for (PropertyDescriptor pd : pds) {
                propName = pd.getName();
                //bean名称为空或者class关键字时没有意义，直接跳过
                if (StringUtil.isNull(propName) || "class".equals(propName)) {
                    continue;
                }

                //根据属性名从request中获取参数值
                value = StringUtil.trimNull(request.getParameter(pd.getName()));
                try {
                    value = URLEncoder.encode(value, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    log.error(e.getMessage());
                }
                Method method = pd.getWriteMethod();
                //参数值不为空时才进行属性注入
                if (method != null && StringUtil.isNotNull(value)) {
                    setSetterVal(bean,method,pd,value);
                }
            }
        } catch (IntrospectionException e) {
            log.error(e.getMessage());
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        } catch (InvocationTargetException e) {
            log.error(e.getMessage());
        }
        return true;
    }


    public static boolean injectPropFromRequest(Object bean, Map<String, Object> map) {
        try {
            PropertyDescriptor[] pds = Introspector
                    .getBeanInfo(bean.getClass()).getPropertyDescriptors();
            String proName = null;
            String value = null;
            //由bean属性名转换的带下划线的列名
            String prop2ColName = null;
            for (PropertyDescriptor pd : pds) {
                proName = pd.getName();
                if (StringUtil.isNull(proName) || "class".equals(proName)) {
                    continue;
                }
                //将bean属性名（按驼峰规则命名）转换成下划线分隔的数据库字段名
                prop2ColName = CamelNameUtils.camel2underscore(proName);
                value = StringUtil.trimNull(map.get(prop2ColName.toLowerCase()));
                Method method = pd.getWriteMethod();
                if (method != null && StringUtil.isNotNull(value)) {
                    setSetterVal(bean, method, pd, value);
                }
            }
        } catch (IntrospectionException e) {
            log.error(e.getMessage());
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        } catch (InvocationTargetException e) {
            log.error(e.getMessage());
        }
        return true;
    }




    private static void setSetterVal(Object bean,Method setter,PropertyDescriptor pd,String value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        setter.setAccessible(true);
        if (pd.getPropertyType().isAssignableFrom(Date.class)) {
            setter.invoke(bean, DateUtil.parseDefDateTime(value));
        } else if(pd.getPropertyType().isAssignableFrom(double.class)) {
            setter.invoke(bean, Double.valueOf(value));
        } else if(pd.getPropertyType().isAssignableFrom(BigDecimal.class)) {
            BigDecimal bd=new BigDecimal(value);
            setter.invoke(bean, bd);
        } else if(pd.getPropertyType().isAssignableFrom(int.class)) {
            Integer bd=new Integer(value);
            setter.invoke(bean, bd.intValue());
        } else if (pd.getPropertyType().isAssignableFrom(Long.class)) {
            setter.invoke(bean, Long.parseLong(value));
        } else if (pd.getPropertyType().isAssignableFrom(long.class)) {
            setter.invoke(bean, Long.parseLong(value));
        } else if (pd.getPropertyType().isAssignableFrom(Byte.class)) {
            setter.invoke(bean, Byte.parseByte(value));
        } else {
            setter.invoke(bean, value);
        }
    }
}
