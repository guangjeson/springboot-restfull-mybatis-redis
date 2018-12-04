package com.gd.itcarrier.helloworld.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gd.itcarrier.helloworld.domain.Customer;


/**
 * springboot2逻辑处理
 * @author yuzg
 *
 */
@RestController
public class CustomerRestController {

    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.GET)
    public Customer findOneCustomer(@PathVariable("id") Long id) {
    	Customer customer = new Customer();
    	customer.setId(id);
    	customer.setCustName("itcarrier");
    	customer.setDescription("新时代搬运工");
    	customer.setOrgId(844L);
        return customer;
    }

}
