package com.keith.rent.web.dao;

import java.util.List;
import java.util.Map;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
public interface IBaseDao {

    List<Map<String, Object>> queryListForMapJdbc(String sql, Map<String, Object> params);
}
