package com.gd.itcarrier.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.gd.itcarrier.api.dao.CityDao;
import com.gd.itcarrier.api.domain.City;
import com.gd.itcarrier.api.service.CityService;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 城市业务逻辑实现类
 * @author yuzg
 */
@Service
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityDao cityDao;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 获取城市逻辑：
     * 如果缓存存在，从缓存中获取城市信息
     * 如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
     */
    public City findCityById(Long id) {
        // 从缓存中获取城市信息
        String key = "city_" + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();

        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            City city = operations.get(key);

            LOGGER.info("CityServiceImpl.findCityById() : 从缓存中获取了城市 >> " + JSON.toJSONString(city));
            return city;
        }

        // 从 DB 中获取城市信息
        City city = cityDao.findById(id);
        if(city!=null)
        {
        	operations.set(key, city);// 插入缓存
        	//operations.set(key, city, 10, TimeUnit.SECONDS);// 插入缓存
        	LOGGER.info("CityServiceImpl.findCityById() : 城市插入缓存 >> " + JSON.toJSONString(city));
        }
        return city;
    }

    @Override
    public Long saveCity(City city) {
        return cityDao.saveCity(city);
    }

    /**
     * 更新城市逻辑：
     * 如果缓存存在，删除
     * 如果缓存不存在，不操作
     */
    @Override
    public Long updateCity(City city) {
        Long ret = cityDao.updateCity(city);

        // 缓存存在，删除缓存
        String key = "city_" + city.getId();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
        	ValueOperations<String, City> operations = redisTemplate.opsForValue();
        	operations.set(key, city);
            LOGGER.info("CityServiceImpl.updateCity() : 更新缓存：city >> " + JSON.toJSONString(city));
        }

        return ret;
    }

    @Override
    public Long deleteCity(Long id) {

        Long ret = cityDao.deleteCity(id);

        // 缓存存在，删除缓存
        String key = "city_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("CityServiceImpl.deleteCity() : 从缓存中删除城市 ID >> " + id);
        }
        else
        	LOGGER.info("CityServiceImpl.deleteCity() : 从缓存中未找到城市 ID >> " + id);
        return ret;
    }

	@Override
	public List<City> findAllCity() {
		// TODO Auto-generated method stub
		 return cityDao.findAllCity();
	}

}
