package com.gd.itcarrier.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gd.itcarrier.api.domain.City;
import com.gd.itcarrier.api.service.CityService;
import com.gd.itcarrier.api.util.ResultBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 城市 Controller 实现 Restful HTTP 服务
 * @author yuzg
 *
 */
@Api(value="web") 
@RestController
public class CustomerRestController {

    @Autowired
    private CityService cityService;

    @ApiOperation(value = "/api/customer/{id}", response = CustomerRestController.class)
    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.GET)
    public City findOneCustomer(@PathVariable("id") Long id) {
    	City city = cityService.findCityById(id);
    	
        return city;
    }

    /*@RequestMapping(value = "/api/customer", method = RequestMethod.GET)
    public List<City> findAllCity() {
        return cityService.findAllCity();
    }*/

    @RequestMapping(value = "/api/customer", method = RequestMethod.POST)
    public void createCustomer(@RequestBody City city) {
        cityService.saveCity(city);
    }

    @RequestMapping(value = "/api/customer", method = RequestMethod.PUT)
    public void modifyCustomer(@RequestBody City city,HttpServletRequest request, HttpServletResponse response) {
        cityService.updateCity(city);
    }
    @ResponseBody
    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.DELETE)
    public ResultBody modifyCustomer(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
       long result = cityService.deleteCity(id);
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
