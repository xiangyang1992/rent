package com.keith.rent.core.jdbcDao.impl;

import com.keith.rent.core.jdbcDao.TenantJdbcDao;
import com.keith.rent.web.dao.BaseDao;
import com.keith.rent.web.sqlutil.SQLUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */

@Repository
public class TenantJdbcDaoImpl extends BaseDao implements TenantJdbcDao {
    @Override
    public List<Map<String ,Object>> findTenantByIds(List<String> ids) {
        String sql = "";
        sql += "Select * from tenant where 1=1 ";
        sql = SQLUtils.AddSearch.create(sql)
                .addIn("tenant_id", ids)
                .build();
        return SQLUtils.getList(this, sql);
    }
}
