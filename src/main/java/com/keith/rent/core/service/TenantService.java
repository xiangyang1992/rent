package com.keith.rent.core.service;

import com.keith.rent.core.entity.Tenant;
import com.keith.rent.core.exception.RentException;

import java.util.List;

/**
 * 租客表(Tenant)表服务接口
 *
 * @author xiangyang
 * @since 2020-05-27 15:03:49
 */
public interface TenantService {

    /**
     * 通过ID查询单条数据
     *
     * @param
     * @param tenantId
     * @return 实例对象
     */
    Tenant queryById(String tenantId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Tenant> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tenant 实例对象
     * @return 实例对象
     */
    int insert(Tenant tenant);

    /**
     * 修改数据
     *
     * @param tenant 实例对象
     * @return 实例对象
     */
    int update(Tenant tenant);

    /**
     * 通过主键删除数据
     *
     * @param  tenantId 主键
     * @return 是否成功
     */
    boolean deleteById(String tenantId);

    List<Tenant> findAll() throws Exception, RentException;

    List<Tenant> queryByTenantName(String tenantName);

    List<Tenant> queryByTenantIds(List<String> tenantIds);
}