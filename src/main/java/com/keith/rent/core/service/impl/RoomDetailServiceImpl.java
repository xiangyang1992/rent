package com.keith.rent.core.service.impl;

import com.keith.rent.core.entity.RoomDetail;
import com.keith.rent.core.dao.RoomDetailDao;
import com.keith.rent.core.service.RoomDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (RoomDetail)表服务实现类
 *
 * @author makejava
 * @since 2020-05-03 14:38:25
 */
@Service("roomDetailService")
public class RoomDetailServiceImpl implements RoomDetailService {
    @Resource
    private RoomDetailDao roomDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public RoomDetail queryById(Integer id) {
        return this.roomDetailDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<RoomDetail> queryAllByLimit(int offset, int limit) {
        return this.roomDetailDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param roomDetail 实例对象
     * @return 实例对象
     */
    @Override
    public RoomDetail insert(RoomDetail roomDetail) {
        this.roomDetailDao.insert(roomDetail);
        return roomDetail;
    }

    /**
     * 修改数据
     *
     * @param roomDetail 实例对象
     * @return 实例对象
     */
    @Override
    public RoomDetail update(RoomDetail roomDetail) {
        this.roomDetailDao.update(roomDetail);
        return this.queryById(roomDetail.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.roomDetailDao.deleteById(id) > 0;
    }
}