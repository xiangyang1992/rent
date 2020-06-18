package com.keith.rent.core.controller;

import com.keith.rent.core.utils.HttpResult;
import com.keith.rent.web.weixinConfig.WeixinUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: Keith
 */
@RestController
@RequestMapping("/weixin/department")
public class TentCtl {


    @GetMapping("/getAllDeptUser")
    public HttpResult getAllDeptUser(@RequestParam (required = true)String departmentId,
                                     @RequestParam (required = false)String fetchChild) throws Exception{
        Object deptUser = WeixinUtil.getAllDeptUser(departmentId, fetchChild);
        return HttpResult.ok(200, "查询成功", deptUser);
    }


}
