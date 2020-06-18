package com.keith.rent.core.controller;

import com.keith.rent.core.entity.SysRole;
import com.keith.rent.core.service.SysRoleService;
import com.keith.rent.core.utils.HttpResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色表(SysRole)表控制层
 *
 * @author xiangyang
 * @since 2020-05-26 11:42:48
 */
@RestController
@RequestMapping("sysRole")
public class SysRoleController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param rId 角色Id
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public HttpResult selectOne(Integer rId) {
        SysRole role = this.sysRoleService.queryById(rId);
        return HttpResult.ok(200, "查询成功", role);
    }

    @GetMapping("getRoleByName")
    public HttpResult getRoleByName(@RequestParam String roleName) {
        SysRole role = this.sysRoleService.queryByRoleName(roleName);
        return HttpResult.ok(200, "查询成功", role);
    }

}