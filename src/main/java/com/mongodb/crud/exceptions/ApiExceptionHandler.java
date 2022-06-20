package com.mongodb.crud.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NoCustomerDataFoundException.class)
    public ResponseEntity<?> employeeAlreadyExists(NoCustomerDataFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<?> employeeAlreadyExists(CustomerAlreadyExistsException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
