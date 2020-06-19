package com.keith.rent.web.test;

import com.keith.rent.core.entity.Tenant;
import com.keith.rent.core.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
@RestController
@RequestMapping("")
public class Test {

    @Autowired
    TenantService tenantService;

    @RequestMapping("/test1")
    public String  test1() throws Exception{
        List<Tenant> list = tenantService.findAll();
        if (list.size() > 0) {
            StringBuffer params = new StringBuffer();
            List<String> statusList = new ArrayList<>();
            for (Tenant tenant : list) {
                String name = tenant.getTenantName();
                String phone = tenant.getApartmentId();
                params.append(name);
                statusList.add(phone);
            }
            if (!StringUtils.isEmpty(params) && params.toString().contains("项羽")) {
                if (statusList.contains("0")) {
                    return "未执行";
                } else if (statusList.contains("-1")) {
                    return "执行失败";
                } else if (statusList.contains("1")) {
                    return "执行中";
                }else{
                    return "执行成功";
                }
            } else {
                return "未执行";
            }

        }
        return "未执行";
    }


}
