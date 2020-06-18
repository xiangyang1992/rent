package com.keith.rent.core.dao;

import com.keith.rent.core.entity.Apartment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Apartment)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-03 14:38:48
 */
@Mapper
public interface ApartmentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Apartment queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Apartment> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param
     * @return 对象列表
     */
    List<Apartment> queryAll();

    /**
     * 新增数据
     *
     * @param apartment 实例对象
     * @return 影响行数
     */
    int insert(Apartment apartment);

    /**
     * 修改数据
     *
     * @param apartment 实例对象
     * @return 影响行数
     */
    int update(Apartment apartment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    Apartment queryByApartmentId(@Param(value = "apartmentId") int apartmentId);

}