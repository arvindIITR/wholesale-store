package com.wholesalestore.wholesalestore.controller;

import com.wholesalestore.wholesalestore.customers.CustomerService;
import com.wholesalestore.wholesalestore.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author arvindkumar
 * created on 21/04/20
 */

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping(value = "/register")
    public ResponseEntity resisterCustomer(@RequestBody Customer customer){
        String email = customer.getEmail();
        if(email != null){
            Customer customerFromDb = customerService.getCustomerByEmail(email);
            if(customerFromDb != null){
                return ResponseEntity.ok().body("Email id already in use");
            }
        }
        try{
            customerService.createCustomer(customer);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().body("200");
    }
}
