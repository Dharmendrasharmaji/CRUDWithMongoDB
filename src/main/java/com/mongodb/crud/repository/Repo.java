package com.mongodb.crud.repository;

import com.mongodb.crud.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface Repo extends MongoRepository<Customer, UUID> {

   Optional<List<Customer>> findByAgeAndName(String age,String name);
   Optional<List<Customer>> findByAge(String age);
   Optional<List<Customer>>findByName(String name);
   Optional<Customer> findByEmail(String email);

}
