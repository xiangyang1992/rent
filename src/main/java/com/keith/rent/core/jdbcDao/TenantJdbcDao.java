package com.keith.rent.core.jdbcDao;

import java.util.List;
import java.util.Map;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
public interface TenantJdbcDao{

    List<Map<String ,Object>> findTenantByIds(List<String > ids);
}
