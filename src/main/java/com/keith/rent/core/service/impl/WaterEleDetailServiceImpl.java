package com.keith.rent.core.service.impl;

import com.keith.rent.core.entity.WaterEleDetail;
import com.keith.rent.core.dao.WaterEleDetailDao;
import com.keith.rent.core.service.WaterEleDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (WaterEleDetail)表服务实现类
 *
 * @author makejava
 * @since 2020-05-03 14:38:58
 */
@Service("waterEleDetailService")
public class WaterEleDetailServiceImpl implements WaterEleDetailService {
    @Resource
    private WaterEleDetailDao waterEleDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WaterEleDetail queryById(Integer id) {
        return this.waterEleDetailDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<WaterEleDetail> queryAllByLimit(int offset, int limit) {
        return this.waterEleDetailDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param waterEleDetail 实例对象
     * @return 实例对象
     */
    @Override
    public WaterEleDetail insert(WaterEleDetail waterEleDetail) {
        this.waterEleDetailDao.insert(waterEleDetail);
        return waterEleDetail;
    }

    /**
     * 修改数据
     *
     * @param waterEleDetail 实例对象
     * @return 实例对象
     */
    @Override
    public WaterEleDetail update(WaterEleDetail waterEleDetail) {
        this.waterEleDetailDao.update(waterEleDetail);
        return this.queryById(waterEleDetail.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.waterEleDetailDao.deleteById(id) > 0;
    }
}