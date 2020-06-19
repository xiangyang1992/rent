package com.keith.rent.core.service.impl;

import com.keith.rent.core.dao.TenantDao;
import com.keith.rent.core.entity.Tenant;
import com.keith.rent.core.exception.RentException;
import com.keith.rent.core.jdbcDao.TenantJdbcDao;
import com.keith.rent.core.service.TenantService;
import com.keith.rent.web.util.MapToBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 租客表(Tenant)表服务实现类
 *
 * @author xiangyang
 * @since 2020-05-27 15:03:49
 */
@Service("tenantService")
@CacheConfig(cacheNames = "tenant")
public class TenantServiceImpl implements TenantService {
    @Resource
    private TenantDao tenantDao;


    @Autowired
    TenantJdbcDao tenantJdbcDao;
    /**
     * 通过ID查询单条数据
     *
     * @param tenantId 主键
     * @return 实例对象
     */
    @Cacheable
    @Override
    public Tenant queryById(String tenantId) {
        return this.tenantDao.queryById(tenantId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Tenant> queryAllByLimit(int offset, int limit) {
        return this.tenantDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tenant 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Tenant tenant) {
        return this.tenantDao.insert(tenant);
    }
    /**
     * 修改数据
     *
     * @param tenant 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Tenant tenant) {
        return this.tenantDao.update(tenant);
    }

    /**
     * 通过主键删除数据
     *
     * @param  tenantId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String tenantId) {
        return this.tenantDao.deleteById(tenantId) > 0;
    }

    @Override
    public List<Tenant> findAll()throws Exception, RentException {
        return this.tenantDao.queryAllTenants();
    }

    @Override
    public List<Tenant> queryByTenantName(String tenantName) {
        return this.tenantDao.queryByTenantName(tenantName);
    }

    @Override
    public List<Tenant> queryByTenantIds(List<String> tenantIds) {
        List<Map<String ,Object>> tenants = tenantJdbcDao.findTenantByIds(tenantIds);
        List<Tenant> tenantsList = new ArrayList<Tenant>();
        for (Map<String, Object> tenant : tenants) {
            Tenant object = MapToBeanUtils.mapToBean(Tenant.class, tenant);
            tenantsList.add(object);
        }

        return tenantsList;
    }
}