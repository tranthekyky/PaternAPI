package com.spring.customersspringmvc.services.impl;

import com.spring.customersspringmvc.models.Customer;
import com.spring.customersspringmvc.repositories.CustomerRepository;
import com.spring.customersspringmvc.services.IGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements IGenerateService<Customer> {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer save(Customer customer) {
       return customerRepository.save(customer);
    }

    @Override
    public void deleteById(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void update(Customer customer, int id) {
       customerRepository.findById(id)
               .map(customer1 -> {
                   customer1.setName(customer.getName());
                   customer1.setAddress(customer.getAddress());
                   customer1.setPhone(customer.getPhone());
                   customer1.setEmail(customer.getEmail());
                   return customerRepository.save(customer1);
               }).orElseGet(() ->{
                   customer.setId(id);
                   return customerRepository.save(customer);
               });
    }

    @Override
    public Boolean existsById(int id) {
        return customerRepository.existsById(id);
    }
}
