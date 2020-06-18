package com.keith.rent.core.controller;

import com.keith.rent.core.annotation.InsertLog;
import com.keith.rent.core.entity.Apartment;
import com.keith.rent.core.service.ApartmentService;
import com.keith.rent.core.utils.HttpResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (Apartment)表控制层--公寓
 *
 * @author makejava
 * @since 2020-05-03 14:38:48
 */
@RestController
@RequestMapping("apartment")
public class ApartmentController {
    /**
     * 服务对象
     */
    @Resource
    private ApartmentService apartmentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public HttpResult selectOne(Integer id) {
        Apartment apartment = this.apartmentService.queryById(id);
        return HttpResult.ok(200, "查询成功", apartment);
    }

    /**
     * @desc: 新增公寓
     * @param:
     * @return:
     * @author: 向阳
     * @date: 2020/5/26
     * @version:
     */
    @InsertLog(value = "公寓新增", module = "公寓管理")
    @PostMapping("addApartment")
    public HttpResult addApartMent(@RequestBody @Valid Apartment apartment) throws Exception {
        int apartmentId = apartment.getApartmentId();
        Apartment apartment1 = apartmentService.queryByApartmentId(apartmentId);
        if (apartment1 != null) {
            return HttpResult.error("公寓ID已存在，请重新添加");
        }
        int flag = apartmentService.insert(apartment);
        if (flag == 1) {
            return HttpResult.ok("新增成功");
        } else {
            return HttpResult.error(999, "新增失败");
        }
    }

    @InsertLog(value = "公寓删除", module = "公寓管理")
    @PostMapping("deleteApartmentById")
    public HttpResult deleteApartment(@RequestParam(value = "apartmentId", required = true) Integer apartmentId) throws Exception {
        Apartment apartment = this.apartmentService.queryByApartmentId(apartmentId);
        if (apartment == null) {
            return HttpResult.error("删除公寓不存在");
        } else {
            boolean flag = this.apartmentService.deleteById(apartmentId);
            if (flag) {
                return HttpResult.ok("删除成功");
            } else {
                return HttpResult.error("删除失败");
            }
        }
    }

    @InsertLog(value = "公寓更新", module = "公寓管理")
    @PostMapping("updateApartment")
    public HttpResult updateApartment(@RequestBody @Valid Apartment apartment) {
        Apartment apartment1 = apartmentService.queryByApartmentId(apartment.getApartmentId());
        if (apartment1 == null) {
            return HttpResult.error("更新失败，待更新公寓不存在！");
        }
        Apartment apartment2 = new Apartment();
        apartment2.setApartmentId(apartment.getApartmentId());
        apartment2.setApartmentMangerId(apartment.getApartmentMangerId());
        apartment2.setApartmentMangerName(apartment.getApartmentMangerName());
        apartment2.setApartmentName(apartment.getApartmentName());
        apartment2.setCreateTime(new Date());
        apartment2.setCreatePerson(apartment.getCreatePerson());
        apartmentService.update(apartment2);
        return HttpResult.ok(200, "更新成功", apartment2);
    }

    /**
     * @desc: 查询所有公寓
     * @param:
     * @return:
     * @author: 向阳
     * @date: 2020/5/26
     * @version:
     */
    @GetMapping("findAll")
    public HttpResult findAll() throws Exception {
        List<Apartment> apartments = apartmentService.findAll();
        return HttpResult.ok(200, "查询成功", apartments);
    }

    @InsertLog(value = "根据名称查询公寓", module = "公寓管理")
    @GetMapping("findApartmentByName")
    public HttpResult findApartmentByName(@RequestBody Map<String ,Object> params) {
        List<Map<String ,Object>> apartments = this.apartmentService.findApartmentByName(params);
        return HttpResult.ok(200, "查询成功", apartments);
    }


//    public static void main(String[] args) {
//        String str = null;
//        str = String.format("HI,%s", "keith");
//        System.out.println(str);
//
//    }

}