package com.keith.rent.web.sqlutil;

import com.keith.rent.web.dao.BaseDao;
import com.keith.rent.web.util.EntityHelper;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: keith
 */
public class SQLUtils {


    public static String getDate(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "TO_DATE('" + sdf.format(date) + "','YYYY-MM-DD HH24:MI:SS')";
    }

    public static <T> T getItem(BaseDao baseDao, String sql, T vo) {
        List<Map<String, Object>> list = baseDao.queryListForMapJdbc(sql, null);
        if (list == null || list.isEmpty()) {
            return null;
        }
        EntityHelper.injectPropFromRequest(vo, list.get(0));
        return vo;
    }

    /**
     * 获取列表
     *
     * @param baseDao
     * @param sql
     * @return
     */
    public static List<Map<String, Object>> getList(BaseDao baseDao, String sql) {
        return baseDao.queryListForMapJdbc(sql, null);
    }


    public static class AddSearch{
        Map<String, Object> map;
        String sql;
        private int type;

        public AddSearch() {
        }

        public AddSearch(Map<String, Object> map, String sql, int type) {
            this.map = map;
            this.sql = sql;
            this.type = type;
        }

        public AddSearch(Map<String, Object> map, String sql) {
            this.map = map;
            this.sql = sql;
        }


        public AddSearch(String sql) {
            this.sql = sql;
            this.type = 1;
        }


        public static AddSearch create(Map<String, Object> map, String sql) {
            return new AddSearch(map, sql);
        }

        public static AddSearch create(String sql) {
            return new AddSearch(sql);
        }

        public AddSearch add(String sqlName, String mapKey) {
            if (type == 0) {
                addMap(sqlName, mapKey);
            } else {
                addValue(sqlName, mapKey);
            }
            return this;
        }

        /**
         * value直接赋值
         *
         * @param sqlName
         * @param mapKey
         */
        private void addValue(String sqlName, String mapKey) {
            if (!StringUtils.isEmpty(mapKey) && !"null".equals(mapKey)) {
                sql += "and " + sqlName + "=" + intercept(mapKey) + "";
            }
        }
        /**
         * map中赋值
         *
         * @param sqlName
         * @param mapKey
         */
        private void addMap(String sqlName, String mapKey) {
            Object object = map.get(mapKey);
            if (!StringUtils.isEmpty(object) && !"null".equals(object)) {
                sql = "and " + sqlName + "=" + intercept(map.get(mapKey));
            }
        }

        public AddSearch addLike(String sqlName, String mapKey) {
            if (type == 0) {
                addLikeMap(sqlName, mapKey);
            } else {
                addLikeValue(sqlName, mapKey);
            }
            return this;
        }

        private void addLikeValue(String sqlName, String mapKey) {
            if (!StringUtils.isEmpty(mapKey) && !"null".equals(mapKey))
                sql += " and " + sqlName + " like '%" + interceptLike(mapKey) + "%'";
        }

        private void addLikeMap(String sqlName, String mapKey) {
            Object object = map.get(mapKey);
            if (!StringUtils.isEmpty(map.get(mapKey)) && !"null".equals(object)) {
                sql += " and " + sqlName + " like '%" + interceptLike(map.get(mapKey)) + "%'";
            }
        }

        private static Object interceptLike(Object o) {
            if (o == null) {
                return o;
            }
            if (o instanceof String) {
                String str = o.toString();
                str = str.replaceAll("([';])+|(--)+", "");
                return str;
            }
            return o;
        }

        private static Object intercept(Object object) {
            return intercept(object, true);
        }

        private static Object intercept(Object object, boolean isY) {
            if (object == null) {
                return null;
            }
            if (object instanceof String) {
                String str = object.toString();
                str = str.replaceAll("([;])+|(--)+", "");
                if ((str.contains("TO_DATE(") || str.contains("TO_CLOB(")) && (str.contains("from") || str.contains("FROM"))) {

                } else if (str.contains("notY")) {
                    str = str.replaceAll("notY", "");
                } else {
                    if (isY) {
                        str = "'" + str + "'";
                    } else {

                    }
                }
                return str;
            }
            return object;
        }

        public String build() {
            outSql(sql);
            return sql;
        }

        private void outSql(String sql) {
            System.out.println("=========================================");
            System.out.println(sql);
            System.out.println("=========================================");
        }

        public AddSearch addIn(String sqlName, List<String> values) {
            if (values == null || values.size() < 1) {
                return this;
            }
            String inSql = "";
            for (String value : values) {
                if (!StringUtils.isEmpty(value)) {
                    inSql += intercept(value) + ",";
                }
            }
            inSql = endClear(inSql);
            sql += " and " + sqlName + " in (" + inSql + ")";
            return this;
        }
    }


    public static String endClear(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        if (str.length() < 1) {
            return str;
        }
        return str.substring(0, str.length() - 1);
    }
}
