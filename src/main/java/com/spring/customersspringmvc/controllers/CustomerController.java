package com.spring.customersspringmvc.controllers;


import com.spring.customersspringmvc.Exception.ApiRequestException;
import com.spring.customersspringmvc.models.Customer;
import com.spring.customersspringmvc.models.ResponseObject;
import com.spring.customersspringmvc.services.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/list")
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getCustomerById(@PathVariable int id) {
        if (!customerService.existsById(id)) throw new ApiRequestException("Customer with id " + id + " does not exist ");
        Customer customer = customerService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject( HttpStatus.OK, "Query Customer successfully", LocalDateTime.now(), customer)
        );
    }
    @PostMapping("/save")
    public ResponseEntity<ResponseObject> saveCustomer(@Valid @RequestBody Customer customer){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject(HttpStatus.OK , "Save Customer successfully", LocalDateTime.now(), customerService.save(customer) )
        );
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateCustomer(@PathVariable int id, @Valid @RequestBody Customer customer) {
        customerService.update(customer, id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(HttpStatus.OK , "Update Customer successfully", LocalDateTime.now(),  customerService.findById(id))
        );
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteCustomer(@PathVariable int id) {
        if (!customerService.existsById(id)) throw new ApiRequestException("Customer with id " + id + " does not exist");
        customerService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(HttpStatus.OK , "Delete Customer ID : " + id + " successfully", LocalDateTime.now(), "")
        ) ;
    }
}
