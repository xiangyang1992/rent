package com.keith.rent.core.service;

import com.keith.rent.core.entity.Apartment;

import java.util.List;
import java.util.Map;

/**
 * (Apartment)表服务接口
 *
 * @author makejava
 * @since 2020-05-03 14:38:48
 */
public interface ApartmentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Apartment queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Apartment> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param apartment 实例对象
     * @return 实例对象
     */
    int insert(Apartment apartment);

    /**
     * 修改数据
     *
     * @param apartment 实例对象
     * @return 实例对象
     */
    Apartment update(Apartment apartment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Apartment queryByApartmentId(int apartmentId);

    List<Apartment> findAll();

    List<Map<String ,Object>> findApartmentByName(Map<String ,Object> params);

    void insertDataToRedis(Object object) throws Exception;
}