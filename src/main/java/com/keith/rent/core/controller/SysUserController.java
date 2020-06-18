package com.keith.rent.core.controller;

import com.keith.rent.core.annotation.InsertLog;
import com.keith.rent.core.entity.SysUser;
import com.keith.rent.core.service.SysUserService;
import com.keith.rent.core.utils.HttpResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUser)表控制层
 *
 * @author makejava
 * @since 2020-05-03 14:37:45
 */
@RestController
@RequestMapping("sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @InsertLog(value = "查询单个用户",module = "用户查询")
    @GetMapping("selectOne")
    public SysUser selectOne(Integer id) {
        return this.sysUserService.queryById(id);
    }

    /**
     * @desc: 查询所有用户
     * @author: 向阳
     * @date: 2020/5/5
     * @version:
     */
    @InsertLog(value = "查询所有用户",module = "用户模块")
    @GetMapping("selectAll")
    public HttpResult selectAll() {
        return HttpResult.ok(200,"查询成功",this.sysUserService.selectAll());
    }

    /**
     * 查询指定行数
     */
    @InsertLog(value = "查询指定行数",module = "用户模块")
    @GetMapping("queryAllByLimit")
    public HttpResult queryAllByLimit( Integer offset,Integer limit) {
        List<SysUser> sysUsers = this.sysUserService.queryAllByLimit(offset, limit);
        return HttpResult.ok(200, "查询成功", sysUsers);
    }


    /**
     * 删除用户
     */
    @InsertLog(value = "删除单个用户",module = "用户模块")
    @PostMapping("deleteById")
    public HttpResult deleteById(Integer uid) {
        Boolean flag = this.sysUserService.deleteById(uid);
        if (flag) {
            return HttpResult.ok("删除成功！");
        } else {
            return HttpResult.error("删除失败！");
        }
    }

    /**
     * 批量删除
     */
    @InsertLog(value = "批量删除用户",module = "用户模块")
    @PostMapping("deleteBatch")
    public HttpResult deleteBatch(Integer[] uids) {
        int num = this.sysUserService.deleteBatch(uids);
        int deleteNum = uids.length;
        if (num == deleteNum) {
            return HttpResult.ok("删除成功");
        }
        return HttpResult.error("删除失败");
    }
}