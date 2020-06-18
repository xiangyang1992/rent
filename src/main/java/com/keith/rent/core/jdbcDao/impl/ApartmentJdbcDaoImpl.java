package com.keith.rent.core.jdbcDao.impl;

import com.keith.rent.core.jdbcDao.ApartmentJdbcDao;
import com.keith.rent.web.dao.BaseDao;
import com.keith.rent.web.sqlutil.SQLUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
@Repository
public class ApartmentJdbcDaoImpl extends BaseDao implements ApartmentJdbcDao {


    @Override
    public List<Map<String ,Object>> findApartmentByName(Map<String ,Object> params) {
        String apartmentName = params.get("apartmentName").toString();
        String sql = "SELECT * FROM apartment WHERE 1=1 ";
        sql = SQLUtils.AddSearch.create(sql)
                .addLike("apartment_name", apartmentName)
                .build();
        return SQLUtils.getList(this, sql);
    }
}
    