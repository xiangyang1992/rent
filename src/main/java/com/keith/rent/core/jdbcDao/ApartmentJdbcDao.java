package com.keith.rent.core.jdbcDao;

import java.util.List;
import java.util.Map;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */

public interface ApartmentJdbcDao {

    List<Map<String ,Object>> findApartmentByName(Map<String ,Object> params);
}
