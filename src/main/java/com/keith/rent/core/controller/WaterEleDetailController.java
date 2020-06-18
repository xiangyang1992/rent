package com.keith.rent.core.controller;

import com.keith.rent.core.entity.WaterEleDetail;
import com.keith.rent.core.service.WaterEleDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (WaterEleDetail)表控制层
 *
 * @author makejava
 * @since 2020-05-03 14:38:58
 */
@RestController
@RequestMapping("waterEleDetail")
public class WaterEleDetailController {
    /**
     * 服务对象
     */
    @Resource
    private WaterEleDetailService waterEleDetailService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public WaterEleDetail selectOne(Integer id) {
        return this.waterEleDetailService.queryById(id);
    }

}