package com.task.employee.Exception;

public class ProjectNotFoundException extends RuntimeException{

   public ProjectNotFoundException(String message){
       super(message);
   }
}
