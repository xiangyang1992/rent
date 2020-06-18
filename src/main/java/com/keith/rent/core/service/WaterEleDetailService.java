package com.keith.rent.core.service;

import com.keith.rent.core.entity.WaterEleDetail;
import java.util.List;

/**
 * (WaterEleDetail)表服务接口
 *
 * @author makejava
 * @since 2020-05-03 14:38:58
 */
public interface WaterEleDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WaterEleDetail queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<WaterEleDetail> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param waterEleDetail 实例对象
     * @return 实例对象
     */
    WaterEleDetail insert(WaterEleDetail waterEleDetail);

    /**
     * 修改数据
     *
     * @param waterEleDetail 实例对象
     * @return 实例对象
     */
    WaterEleDetail update(WaterEleDetail waterEleDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}