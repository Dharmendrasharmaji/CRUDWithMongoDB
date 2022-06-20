package com.mongodb.crud.service;

import com.mongodb.crud.entity.Customer;
import com.mongodb.crud.exceptions.CustomerAlreadyExistsException;
import com.mongodb.crud.exceptions.NoCustomerDataFoundException;
import com.mongodb.crud.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class CutomerServiceImpl implements CustomerService{

    @Autowired
    private Repo repo;


    @Override
    public List<Customer> getAllCustomer(String age,String name) {

        if (age!=null && name!=null){

            Optional<List<Customer>> byCustomerAgeAndName = repo.findByAgeAndName(age,name.toUpperCase());
            if (byCustomerAgeAndName.get().isEmpty()){
                return getCustomers();
            }
            return byCustomerAgeAndName.get();

        }else if (age!=null){

            Optional<List<Customer>> byCustomerAge = repo.findByAge(age);
            if (byCustomerAge.get().isEmpty()){
                return getCustomers();
            }
            return byCustomerAge.get();

        }else if (name!=null){

            Optional<List<Customer>> byCustomerName= repo.findByName(name.toUpperCase());
            if (byCustomerName.get().isEmpty()){
                return getCustomers();
            }
            return byCustomerName.get();

        }

        return getCustomers();
    }

    public List<Customer> getCustomers(){
        List<Customer> customers = repo.findAll();
        if (customers.isEmpty()){
            throw new NoCustomerDataFoundException("Cutomers data are not present.");
        }
        return customers;
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Optional<Customer> byemailId = repo.findByEmail(email.toUpperCase());
        return byemailId.orElseThrow(()->new NoCustomerDataFoundException("Cutomer data is not present."));
    }

    @Override
    public Customer addCustomer(Customer customer) {
        Optional<Customer> byEmail = repo.findByEmail(customer.getEmail().toUpperCase());
        if(byEmail.isPresent()){
            throw new CustomerAlreadyExistsException("Customer with given email is already exist.");
        }
        customer.setId(UUID.randomUUID());
        customer.setEmail(customer.getEmail().toUpperCase());
        customer.setName(customer.getName().toUpperCase());
        Customer customer1 = repo.save(customer);
        return customer1;
    }

    @Override
    public Customer deleteCustomer(UUID id) {
        Optional<Customer> byId = repo.findById(id);
        if(byId.isEmpty()){
            throw new NoCustomerDataFoundException("Customer with given id doesn't exist.");
        }
        repo.delete(byId.get());
        return byId.get();
    }

    @Override
    public Customer updateCustomer(Customer customer, UUID id) {
        Optional<Customer> byEmail = repo.findById(id);
        if(byEmail.isEmpty()){
            throw new NoCustomerDataFoundException("Customer with given id doesn't exist.");
        }
        customer.setId(id);
        customer.setEmail(customer.getEmail().toUpperCase());
        customer.setName(customer.getName().toUpperCase());
        return repo.save(customer);
    }

    @Override
    public Customer patchCustomer(UUID id, Map<Object,Object> changes) {
        Optional<Customer> byEmail = repo.findById(id);
        if(byEmail.isEmpty()){
            throw new NoCustomerDataFoundException("Customer with given id doesn't exist.");
        }
        changes.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Customer.class, (String) key);
            if (field!=null){
                field.setAccessible(true);
                ReflectionUtils.setField(field,byEmail.get(),value);
            }
        });
        return repo.save(byEmail.get());
    }
}
