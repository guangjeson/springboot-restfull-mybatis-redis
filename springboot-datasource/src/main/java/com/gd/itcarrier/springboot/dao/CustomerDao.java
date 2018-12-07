package com.gd.itcarrier.springboot.dao;

import org.apache.ibatis.annotations.Param;

import com.gd.itcarrier.springboot.entity.Customer;

import java.util.List;

/**
 * DAO接口类
 *
 * @author yuzg
 */
public interface CustomerDao {

    List<Customer> findAllCustomer();
    Customer findById(@Param("id") Long id);
    Long saveCustomer(Customer city);
    Long updateCustomer(Customer city);
    Long deleteCustomer(Long id);
}
