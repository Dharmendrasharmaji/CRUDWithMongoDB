package com.mongodb.crud.service;

import com.mongodb.crud.entity.Customer;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CustomerService {

    List<Customer> getAllCustomer(String age, String name);
    Customer getCustomerByEmail(String email);
    Customer addCustomer(Customer customer);
    Customer deleteCustomer(UUID id);
    Customer updateCustomer(Customer customer,UUID id);
    Customer patchCustomer(UUID id, Map<Object,Object> changes);
}
