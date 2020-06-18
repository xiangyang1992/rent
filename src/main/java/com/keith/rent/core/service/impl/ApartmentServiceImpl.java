package com.keith.rent.core.service.impl;

import com.keith.rent.core.dao.ApartmentDao;
import com.keith.rent.core.entity.Apartment;
import com.keith.rent.core.jdbcDao.ApartmentJdbcDao;
import com.keith.rent.core.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Apartment)表服务实现类
 *
 * @author makejava
 * @since 2020-05-03 14:38:48
 */
@Service("apartmentService")
public class ApartmentServiceImpl implements ApartmentService {
    @Resource
    private ApartmentDao apartmentDao;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private ApartmentJdbcDao apartmentJdbcDao;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Apartment queryById(Integer id) {
        return this.apartmentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Apartment> queryAllByLimit(int offset, int limit) {
        return this.apartmentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param apartment 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Apartment apartment) {
        return this.apartmentDao.insert(apartment);
    }

    /**
     * 修改数据
     *
     * @param apartment 实例对象
     * @return 实例对象
     */
    @Override
    public Apartment update(Apartment apartment) {
        this.apartmentDao.update(apartment);
        return this.queryById(apartment.getApartmentId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.apartmentDao.deleteById(id) > 0;
    }

    @Override
    public Apartment queryByApartmentId(int apartmentId) {
        return this.apartmentDao.queryByApartmentId(apartmentId);
    }

    @Override
    public List<Apartment> findAll() {
        return this.apartmentDao.queryAll();
    }

    @Override
    public List<Map<String ,Object>> findApartmentByName(Map<String ,Object> params) {
        return apartmentJdbcDao.findApartmentByName(params);
    }

    @Override
    public void insertDataToRedis(Object object) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("tenant1", "向阳");
        params.put("tenant2", "潘艳桃");
        redisTemplate.opsForHash();
    }
}