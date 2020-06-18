package com.keith.rent.core.dao;

import com.keith.rent.core.entity.RoomDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (RoomDetail)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-03 14:38:25
 */
@Mapper
public interface RoomDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RoomDetail queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<RoomDetail> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param roomDetail 实例对象
     * @return 对象列表
     */
    List<RoomDetail> queryAll(RoomDetail roomDetail);

    /**
     * 新增数据
     *
     * @param roomDetail 实例对象
     * @return 影响行数
     */
    int insert(RoomDetail roomDetail);

    /**
     * 修改数据
     *
     * @param roomDetail 实例对象
     * @return 影响行数
     */
    int update(RoomDetail roomDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}