package com.keith.rent.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
@Repository
public class BaseDao implements IBaseDao {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public List<Map<String, Object>> queryListForMapJdbc(String sql, Map<String, Object> params) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Map<String, Object>> data = new ArrayList<>();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        conn = sqlSession.getConnection();
        try {
            pstm = conn.prepareStatement(sql);
            if (params != null) {
                int num = 1;
                for (Iterator iterator = params.keySet().iterator(); iterator.hasNext();) {
                    String key = (String) iterator.next();
                    pstm.setObject(num++, params.get(key));
                }
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                Map<String, Object> dataMap = new HashMap<>();
                int count = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= count; i++) {
                    String colName = rs.getMetaData().getColumnName(i);
                    Object obj = rs.getObject(colName);
                    dataMap.put(colName, obj);
                }
                data.add(dataMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return data;
    }
}
