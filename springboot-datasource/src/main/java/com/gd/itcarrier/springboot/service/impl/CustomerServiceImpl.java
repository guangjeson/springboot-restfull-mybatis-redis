package com.gd.itcarrier.springboot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.gd.itcarrier.springboot.dao.CustomerDao;
import com.gd.itcarrier.springboot.entity.Customer;
import com.gd.itcarrier.springboot.service.CustomerService;

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
    
    public Customer findCustomerById(Long id) {
        // 从 数据库中获取信息
        Customer customer = customerDao.findById(id);
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
        return ret;
    }

    @Override
    public Long deleteCustomer(Long id) {
        Long ret = customerDao.deleteCustomer(id);
        return ret;
    }

	@Override
	public List<Customer> findAllCustomer() {
		// TODO Auto-generated method stub
		 return customerDao.findAllCustomer();
	}

}
