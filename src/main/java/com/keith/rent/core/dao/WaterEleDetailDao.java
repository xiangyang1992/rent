package com.keith.rent.core.dao;

import com.keith.rent.core.entity.WaterEleDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (WaterEleDetail)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-03 14:38:58
 */
@Mapper
public interface WaterEleDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WaterEleDetail queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<WaterEleDetail> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param waterEleDetail 实例对象
     * @return 对象列表
     */
    List<WaterEleDetail> queryAll(WaterEleDetail waterEleDetail);

    /**
     * 新增数据
     *
     * @param waterEleDetail 实例对象
     * @return 影响行数
     */
    int insert(WaterEleDetail waterEleDetail);

    /**
     * 修改数据
     *
     * @param waterEleDetail 实例对象
     * @return 影响行数
     */
    int update(WaterEleDetail waterEleDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}