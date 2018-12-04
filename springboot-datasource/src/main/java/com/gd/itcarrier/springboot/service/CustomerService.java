package com.gd.itcarrier.springboot.service;

import java.util.List;

import com.gd.itcarrier.springboot.domain.Customer;

/**
 * 逻辑处理接口类
 * @author yuzg
 */
public interface CustomerService {
	
    List<Customer> findAllCustomer();

    /**
     * 根据id查询信息
     *
     * @param id
     * @return
     */
    Customer findCustomerById(Long id);

    /**
     * 新增信息
     *
     * @param Customer
     * @return
     */
    Long saveCustomer(Customer city);

    /**
     * 更新信息
     *
     * @param Customer
     * @return
     */
    Long updateCustomer(Customer customer);

    /**
     * 根据id删除信息
     *
     * @param id
     * @return
     */
    Long deleteCustomer(Long id);
}
