package com.keith.rent.core.controller;

import com.keith.rent.core.entity.RoomDetail;
import com.keith.rent.core.service.RoomDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (RoomDetail)表控制层
 *
 * @author makejava
 * @since 2020-05-03 14:38:25
 */
@RestController
@RequestMapping("roomDetail")
public class RoomDetailController {
    /**
     * 服务对象
     */
    @Resource
    private RoomDetailService roomDetailService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public RoomDetail selectOne(Integer id) {
        return this.roomDetailService.queryById(id);
    }

}