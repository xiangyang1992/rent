package com.keith.rent.core.controller;

import com.keith.rent.core.annotation.InsertLog;
import com.keith.rent.core.entity.Room;
import com.keith.rent.core.service.RoomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Room)表控制层--房间信息
 *
 * @author makejava
 * @since 2020-05-03 14:38:37
 */
@RestController
@RequestMapping("room")
public class RoomController {
    /**
     * 服务对象
     */
    @Resource
    private RoomService roomService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @InsertLog(value = "查询单个房间",module = "住房信息")
    @GetMapping("selectOne")
    public Room selectOne(@RequestParam Integer id) {
        return this.roomService.queryById(id);
    }

}