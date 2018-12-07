package com.gd.itcarrier.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.gd.itcarrier.api.dao.CustomerDao;
import com.gd.itcarrier.api.entity.Customer;
import com.gd.itcarrier.api.service.CustomerService;

import java.util.List;

/**
 * 业务逻辑实现类
 * @author yuzg
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 如果从缓存中获取到，直接返回缓存数据。如果缓存不存在，从数据库中读取并插入到缓存
     */
    public Customer findCustomerById(Long id) {
        // 从缓存中获取信息
        String key = "Customer_" + id;
        ValueOperations<String, Customer> operations = redisTemplate.opsForValue();

        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
        	Customer customer = operations.get(key);

            LOGGER.info("CustomerServiceImpl.findCustomerById() : 从缓存中获取了信息 >> " + JSON.toJSONString(customer));
            return customer;
        }

        // 从 数据库中获取信息
        Customer customer = customerDao.findById(id);
        if(customer!=null)
        {
        	operations.set(key, customer);// 插入缓存
        	//operations.set(key, customer, 10, TimeUnit.SECONDS);// 插入缓存
        	LOGGER.info("CustomerServiceImpl.findCustomerById() : 插入缓存 >> " + JSON.toJSONString(customer));
        }
        return customer;
    }

    @Override
    public Long saveCustomer(Customer customer) {
        return customerDao.saveCustomer(customer);
    }

    /**
     * 更新逻辑：
     * 如果缓存存在，删除
     * 如果缓存不存在，不操作
     */
    @Override
    public Long updateCustomer(Customer customer) {
        Long ret = customerDao.updateCustomer(customer);

        // 缓存存在，删除缓存
        String key = "customer_" + customer.getId();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
        	ValueOperations<String, Customer> operations = redisTemplate.opsForValue();
        	operations.set(key, customer);
            LOGGER.info("CustomerServiceImpl.updateCustomer() : 更新缓存：Customer >> " + JSON.toJSONString(customer));
        }

        return ret;
    }

    @Override
    public Long deleteCustomer(Long id) {

        Long ret = customerDao.deleteCustomer(id);

        // 缓存存在，删除缓存
        String key = "customer_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("CustomerServiceImpl.deleteCustomer() : 从缓存中删除ID >> " + id);
        }
        else
        	LOGGER.info("CustomerServiceImpl.deleteCustomer() : 从缓存中未找到 ID >> " + id);
        return ret;
    }

	@Override
	public List<Customer> findAllCustomer() {
		// TODO Auto-generated method stub
		 return customerDao.findAllCustomer();
	}

}
