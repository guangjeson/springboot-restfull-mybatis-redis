package com.gd.itcarrier.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gd.itcarrier.api.entity.Customer;
import com.gd.itcarrier.api.service.CustomerService;
import com.gd.itcarrier.api.util.ResultBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 业务处理控制层，实现 Restful服务
 * @author yuzg
 *
 */
@Api(value="web") 
@RestController
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "/api/customer/{id}", response = CustomerRestController.class)
    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.GET)
    public Customer findOneCustomer(@PathVariable("id") Long id) {
    	Customer customer = customerService.findCustomerById(id);
    	
        return customer;
    }

    /*@RequestMapping(value = "/api/customer", method = RequestMethod.GET)
    public List<Customer> findAllCustomer() {
        return customerService.findAllCustomer();
    }*/

    @RequestMapping(value = "/api/customer", method = RequestMethod.POST)
    public void createCustomer(@RequestBody Customer customer) {
    	customerService.saveCustomer(customer);
    }

    @RequestMapping(value = "/api/customer", method = RequestMethod.PUT)
    public void modifyCustomer(@RequestBody Customer customer,HttpServletRequest request, HttpServletResponse response) {
    	customerService.updateCustomer(customer);
    }
    @ResponseBody
    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.DELETE)
    public ResultBody modifyCustomer(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
       long result = customerService.deleteCustomer(id);
       ResultBody body = new ResultBody();
       if(result==0)
       {
    	   response.setStatus(400);
    	   body.setCode("400");
    	   body.setMessage("删除失败!");
       }
       else{
    	   body.setCode("200");
    	   body.setMessage("删除成功!");
       }
       return body;
    }
}
