package com.task.employee.Exception;


import org.springframework.stereotype.Component;


public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String message){
        super(message);
    }
}
