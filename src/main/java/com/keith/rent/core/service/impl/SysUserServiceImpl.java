package com.keith.rent.core.service.impl;

import com.keith.rent.core.entity.SysUser;
import com.keith.rent.core.dao.SysUserDao;
import com.keith.rent.core.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUser)表服务实现类
 *
 * @author makejava
 * @since 2020-05-03 14:37:45
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    @Override
    public SysUser queryById(Integer uid) {
        return this.sysUserDao.queryById(uid);
    }

    @Override
    public SysUser queryByName(String username) {
        return this.sysUserDao.queryByName(username);
    }


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysUser> queryAllByLimit(Integer offset, Integer limit) {
        return this.sysUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SysUser sysUser) {
        return this.sysUserDao.insert(sysUser);
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser update(SysUser sysUser) {
        this.sysUserDao.update(sysUser);
        return this.queryById(sysUser.getUid());
    }

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer uid) {
        return this.sysUserDao.deleteById(uid) > 0;
    }

    /**
     * @des 查询所有用户
     *
     * @param
     * @return
     */
    @Override
    public List<SysUser> selectAll() {
        return this.sysUserDao.selectAll();
    }

    @Override
    public int deleteBatch(Integer[]  uids) {
        return this.sysUserDao.deleteBatch(uids);
    }
}