package com.mongodb.crud.controller;

import com.mongodb.crud.entity.Customer;
import com.mongodb.crud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/")
public class Customer_Controller {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getCustormers(@RequestParam(value = "age",required = false) String age, @RequestParam(value = "name",required = false) String name) {
        List<Customer> Cutomers = customerService.getAllCustomer(age,name);
        return new ResponseEntity<>(Cutomers, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable("email") String email ) {
        if (email==null){
            return new ResponseEntity<>("Email is mandatory",HttpStatus.BAD_REQUEST);
        }
        Customer customerByEmail = customerService.getCustomerByEmail(email);
        return new ResponseEntity<>(customerByEmail, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        if (customer == null) {
            return new ResponseEntity<>("Customer data wasn't provided", HttpStatus.BAD_REQUEST);
        } else {
            Customer customer1 = customerService.addCustomer(customer);
            return new ResponseEntity<>(customer1, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable UUID id) {
        Customer customer = customerService.deleteCustomer(id);
        return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer,@PathVariable UUID id){
        if (customer == null) {
            return new ResponseEntity<>("Customer data wasn't provided", HttpStatus.BAD_REQUEST);
        } else {
            Customer customer1 = customerService.updateCustomer(customer,id);
            return new ResponseEntity<>(customer1, HttpStatus.CREATED);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchCustomer(@PathVariable UUID id, @RequestBody Map<Object,Object> changes){
        Customer customer = customerService.patchCustomer(id, changes);
        return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);
    }



}
