package com.ezgicinan.springbootapplication.controller;

import com.ezgicinan.springbootapplication.model.Customer;
import com.ezgicinan.springbootapplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping(path = "/createCustomer")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping(path = "/getAllCustomer")
    public List<Customer> getAllCustomer() {
        try {
            List<Customer> allCustomers = customerService.getAllCustomers();
            return  allCustomers;
        } catch (Exception e) {
            return null;
        }
    }
}
