package com.task.employee.Exception;

public class InvalidEntryException extends RuntimeException{
    public InvalidEntryException(String message){
        super(message);
    }
}
