package com.keith.rent.core.dao;

import com.keith.rent.core.entity.Tenant;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 租客表(Tenant)表数据库访问层
 *
 * @author xiangyang
 * @since 2020-05-27 15:03:49
 */
public interface TenantDao {

    /**
     * 通过ID查询单条数据
     *
     * @param  tenantId 主键
     * @return 实例对象
     */
    Tenant queryById(String tenantId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Tenant> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tenant 实例对象
     * @return 对象列表
     */
    List<Tenant> queryAll(Tenant tenant);

    /**
     * 查询所有租客信息
     * @return
     */
    List<Tenant> queryAllTenants();
    /**
     * 新增数据
     *
     * @param tenant 实例对象
     * @return 影响行数
     */
    int insert(Tenant tenant);

    /**
     * 修改数据
     *
     * @param tenant 实例对象
     * @return 影响行数
     */
    int update(Tenant tenant);

    /**
     * 通过主键删除数据
     *
     * @param  tenantId 主键
     * @return 影响行数
     */
    int deleteById(String  tenantId);

    List<Tenant> queryByTenantName(String tenantName);
}