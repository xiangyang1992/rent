package com.keith.rent.core.service;

import com.keith.rent.core.entity.SysUser;
import java.util.List;

/**
 * (SysUser)表服务接口
 *
 * @author makejava
 * @since 2020-05-03 14:37:44
 */
public interface SysUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    SysUser queryById(Integer uid);


    SysUser queryByName(String username);
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysUser> queryAllByLimit(Integer offset, Integer limit);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    int insert(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer uid);

    /**
     * @param
     * @return
     * @des 查询所有用户
     */
    List<SysUser> selectAll();

    int deleteBatch(Integer[]  uids);
}