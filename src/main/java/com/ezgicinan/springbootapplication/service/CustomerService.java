package com.ezgicinan.springbootapplication.service;
import com.ezgicinan.springbootapplication.exceptions.CustomerNotExistException;
import com.ezgicinan.springbootapplication.model.Customer;
import com.ezgicinan.springbootapplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(Integer customerId) throws CustomerNotExistException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            if(!optionalCustomer.isPresent())
                throw new CustomerNotExistException("Customer id is not exist " + customerId);
            return optionalCustomer.get();
    }

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
       return customerRepository.findAll();
    }

}
