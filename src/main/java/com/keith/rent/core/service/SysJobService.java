package com.keith.rent.core.service;

import com.keith.rent.core.entity.SysJob;
import java.util.List;

/**
 * 定时任务(SysJob)表服务接口
 *
 * @author xiangyang
 * @since 2020-06-05 17:48:08
 */
public interface SysJobService {

    /**
     * 通过ID查询单条数据
     *
     * @param taskId 主键
     * @return 实例对象
     */
    SysJob queryById(Integer taskId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysJob> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysJob 实例对象
     * @return 实例对象
     */
    SysJob insert(SysJob sysJob);

    /**
     * 修改数据
     *
     * @param sysJob 实例对象
     * @return 实例对象
     */
    SysJob update(SysJob sysJob);

    /**
     * 通过主键删除数据
     *
     * @param taskId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer taskId);

    List<SysJob> selectTask(SysJob sysJob);
}