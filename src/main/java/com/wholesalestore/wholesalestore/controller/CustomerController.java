package com.wholesalestore.wholesalestore.controller;

import com.wholesalestore.wholesalestore.constants.HttpStatusCode;
import com.wholesalestore.wholesalestore.customers.CustomerService;
import com.wholesalestore.wholesalestore.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author arvindkumar
 * created on 21/04/20
 */

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping(value = "/register")
    public Map<String, Object> resisterCustomer(@RequestBody Customer customer){
        Map<String, Object> response = new HashMap<>();
        String email = customer.getEmail();
        if(email != null){
            Customer customerFromDb = customerService.getCustomerByEmail(email);
            if(customerFromDb != null){
                response.put("response", "Email id already in use");
                response.put("status", 201);
                return response;
            }
        }
        Customer persistedCustomer;
        try{
            persistedCustomer = customerService.createCustomer(customer);
        }catch (Exception e){
            e.printStackTrace();
            response.put("response", "Internal server error");
            response.put("status", HttpStatusCode.INTERNAL_SERVER_ERROR);
            return response;
        }
        if(persistedCustomer != null){
            response.put("response", "Successfully registered.");
            response.put("status", HttpStatusCode.SUCCESS);
        }
        return response;
    }
}
