package com.keith.rent.core.controller;

import com.google.common.collect.Lists;
import com.keith.rent.core.annotation.InsertLog;
import com.keith.rent.core.entity.Room;
import com.keith.rent.core.entity.Tenant;
import com.keith.rent.core.service.RoomService;
import com.keith.rent.core.service.TenantService;
import com.keith.rent.core.utils.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 租客表(Tenant)表控制层
 *
 * @author xiangyang
 * @since 2020-05-27 15:03:50
 */
@RestController
@RequestMapping("tenant")
public class TenantController {
    /**
     * 服务对象
     */
    @Resource
    private TenantService tenantService;

    @Autowired
    private RoomService roomService;

    /**
     * 通过主键查询单条数据
     *
     * @param tenantId 主键
     * @return 单条数据
     */
    @GetMapping("selectOne.do")
    public Tenant selectOne(String tenantId) {
        return this.tenantService.queryById(tenantId);
    }

    @InsertLog(value = "查询所有租户信息", module = "租户管理")
    @GetMapping("findAll.do")
    public HttpResult findAll() throws Exception {
        List<Tenant> tenants = this.tenantService.findAll();
        return HttpResult.ok(200, "查询成功", tenants);
    }

    @InsertLog(value = "新增租户信息", module = "租户管理")
    @PostMapping("addTenant.do")
    public HttpResult addTenant(@RequestBody @Valid Tenant tenant) {
        List<Tenant> tenant1 = this.tenantService.queryByTenantName(tenant.getTenantName());
        if (tenant1 != null && tenant1.size() > 0) {
            return HttpResult.error("租客已录入，请勿重复新增！");
        } else {
            int flag = this.tenantService.insert(tenant);
            if (flag == 1) {
                return HttpResult.ok("新增成功");
            }
            return HttpResult.error("新增失败");
        }
    }

    @InsertLog(value = "更新租户信息", module = "租户管理")
    @PostMapping("updateTenant.do")
    public HttpResult updateTenant(@RequestBody @Valid Tenant tenant) {
        List<Tenant> tenant1 = this.tenantService.queryByTenantName(tenant.getTenantName());
        if (tenant1 == null) {
            return HttpResult.error("更新用户不存在");
        }
        int flag = this.tenantService.update(tenant);
        if (flag == 1) {
            return HttpResult.ok("更新成功");
        }
        return HttpResult.error("更新失败");
    }

    @InsertLog(value = "查询单个租户信息", module = "租户管理")
    @GetMapping("getTenantByName.do")
    public HttpResult getTenantByName(@RequestParam String tenantName) {
        List<Tenant> tenant = this.tenantService.queryByTenantName(tenantName);
        if (tenant != null) {
            for (Tenant tenant1 : tenant) {
                Room roomInfo = roomService.queryById(tenant1.getRoomId());
                tenant1.setRoom(roomInfo);
            }
            return HttpResult.ok(200, "查询成功", tenant);
        }
        return HttpResult.error("查询失败");
    }

    @InsertLog(value = "删除用户", module = "租户管理")
    @PostMapping("deleteTenants.do")
    public HttpResult deleteTenants(@RequestParam String tenantIds) {
        if (tenantIds.equals("") || tenantIds == null) {
            return HttpResult.error("删除用户ID不能为空");
        }
        String[] tenantIds1 = tenantIds.split(",");
        int index = 0;
        for (int i = 0; i < tenantIds1.length; i++) {
            boolean flag = this.tenantService.deleteById(tenantIds1[i]);
            if (flag) {
                index++;
            } else {
                return HttpResult.error(303, "删除租户失败", tenantIds1[i]);
            }
        }
        if (index == tenantIds1.length) {
            return HttpResult.ok("批量删除成功");
        }
        return HttpResult.error("批量删除失败");
    }

    @InsertLog(value = "文件下载", module = "租户管理")
    @PostMapping("/exportExcle")
    public HttpResult exportExcle(@RequestParam String tenantIds) {
        if (tenantIds.length() <= 0) {
            return HttpResult.error("导出人员不能为空");
        }
        String[] ids = tenantIds.split(",");
        List<String > ids2 = Lists.newArrayList(ids);
        List<Tenant> tenants = tenantService.queryByTenantIds(ids2);
        return HttpResult.ok(200,"成功", tenants);
    }
}
