package com.keith.rent.core.service;

import com.keith.rent.core.entity.RoomDetail;
import java.util.List;

/**
 * (RoomDetail)表服务接口
 *
 * @author makejava
 * @since 2020-05-03 14:38:25
 */
public interface RoomDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RoomDetail queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<RoomDetail> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param roomDetail 实例对象
     * @return 实例对象
     */
    RoomDetail insert(RoomDetail roomDetail);

    /**
     * 修改数据
     *
     * @param roomDetail 实例对象
     * @return 实例对象
     */
    RoomDetail update(RoomDetail roomDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}