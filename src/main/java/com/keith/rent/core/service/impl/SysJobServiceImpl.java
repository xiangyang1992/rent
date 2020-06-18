package com.keith.rent.core.service.impl;

import com.keith.rent.core.entity.SysJob;
import com.keith.rent.core.dao.SysJobDao;
import com.keith.rent.core.service.SysJobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务(SysJob)表服务实现类
 *
 * @author xiangyang
 * @since 2020-06-05 17:48:08
 */
@Service("sysJobService")
public class SysJobServiceImpl implements SysJobService {
    @Resource
    private SysJobDao sysJobDao;

    /**
     * 通过ID查询单条数据
     *
     * @param taskId 主键
     * @return 实例对象
     */
    @Override
    public SysJob queryById(Integer taskId) {
        return this.sysJobDao.queryById(taskId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysJob> queryAllByLimit(int offset, int limit) {
        return this.sysJobDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysJob 实例对象
     * @return 实例对象
     */
    @Override
    public SysJob insert(SysJob sysJob) {
        this.sysJobDao.insert(sysJob);
        return sysJob;
    }

    /**
     * 修改数据
     *
     * @param sysJob 实例对象
     * @return 实例对象
     */
    @Override
    public SysJob update(SysJob sysJob) {
        this.sysJobDao.update(sysJob);
        return this.queryById(sysJob.getTaskId());
    }

    /**
     * 通过主键删除数据
     *
     * @param taskId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer taskId) {
        return this.sysJobDao.deleteById(taskId) > 0;
    }

    @Override
    public List<SysJob> selectTask(SysJob sysJob) {
        return this.sysJobDao.queryAll(sysJob);
    }
}