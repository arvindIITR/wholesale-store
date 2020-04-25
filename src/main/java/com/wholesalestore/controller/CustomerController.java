package com.wholesalestore.controller;

import com.wholesalestore.constants.HttpStatusCode;
import com.wholesalestore.constants.RegistrationConstant;
import com.wholesalestore.entities.Customer;
import com.wholesalestore.model.Login;
import com.wholesalestore.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author arvindkumar
 */

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Registration API
     *
     * @param customer
     * @return Map<String, Object>
     */
    @PostMapping(value = "/register")
    public Map<String, Object> resisterCustomer(@RequestBody Customer customer) {
        Map<String, Object> response = new HashMap<>();
        String email = customer.getEmail();
        if (email != null) {
            Customer customerFromDb = customerService.getCustomerByEmail(email);
            if (customerFromDb != null) {
                response.put(HttpStatusCode.RESPONSE, RegistrationConstant.EMAIL_EXIST);
                response.put(HttpStatusCode.STATUS, 201);
                return response;
            }
        }
        Customer persistedCustomer;
        try {
            persistedCustomer = customerService.createCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
            response.put(HttpStatusCode.RESPONSE, "Internal server error");
            response.put(HttpStatusCode.STATUS, HttpStatusCode.INTERNAL_SERVER_ERROR);
            return response;
        }
        if (persistedCustomer != null) {
            response.put(HttpStatusCode.RESPONSE, RegistrationConstant.REGISTERED);
            response.put(HttpStatusCode.STATUS, HttpStatusCode.SUCCESS);
        }
        return response;
    }

    /**
     * Login API
     *
     * @param login
     */
    @PostMapping(value = "/login")
    public Map<String, Object> login(@RequestBody Login login) {
        Map<String, Object> response = new HashMap<>();
        if (login == null) {
            response.put(HttpStatusCode.RESPONSE, RegistrationConstant.EMPTY_CREDENTIALS);
            response.put(HttpStatusCode.STATUS, HttpStatus.BAD_REQUEST);
            return response;
        }
        String email = login.getEmail();
        String pass = login.getPassword();
        if (email == null) {
            response.put(HttpStatusCode.RESPONSE, RegistrationConstant.EMPTY_USER);
            response.put(HttpStatusCode.STATUS, HttpStatus.BAD_REQUEST);
            return response;
        }
        if (pass == null) {
            response.put(HttpStatusCode.RESPONSE, RegistrationConstant.EMPTY_PASSWORD);
            response.put(HttpStatusCode.STATUS, HttpStatus.BAD_REQUEST);
            return response;
        }
        Customer userFromDB;
        try {
            userFromDB = customerService.getCustomerByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            response.put(HttpStatusCode.RESPONSE, "Internal server error");
            response.put(HttpStatusCode.STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
        if (userFromDB == null) {
            throw new RuntimeException("User does not exist.");
        }
        if (!userFromDB.getPassword().equals(login.getPassword())) {
            throw new RuntimeException("Password mismatch.");
        }
        if (userFromDB != null && userFromDB.getPassword().equals(login.getPassword())) {
            response.put(HttpStatusCode.RESPONSE, "Login successful");
            response.put(HttpStatusCode.STATUS, HttpStatusCode.SUCCESS);
        }
        return response;
    }
}
