package com.task.employee.Service;

import com.task.employee.Repository.EmployeeRepo;
import com.task.employee.Domain.Employee;
import com.task.employee.Exception.GeneratedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmployee {

    @Autowired
    EmployeeRepo employeeRepo;
    public ResponseEntity<List<Employee>> findAll(){
        try{
            return new ResponseEntity<>(employeeRepo.findAll(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public Optional<Employee> findByID(Integer id) {
        return employeeRepo.findById(id);
    }

    public String deleteEmployee(Integer id) {
      try {
          if (employeeRepo.existsById(id)) {
              employeeRepo.deleteById(id);
              return "Employee deleted successfully.";
          } else {
              return "failed";
          }
      } catch (EmptyResultDataAccessException ex) {
            return "Employee with ID " + id + " not found.";
        }
    }

    public boolean employeeExists(Integer id) {

      return employeeRepo.existsById(id);
    }

    public List<Employee> getAllemp(){
    return employeeRepo.findAll();
}


    public Employee updateEmployee(Integer id, Employee employeeRequest) {
        Employee employee = null;
        try {
            employee = employeeRepo.findById(id)
                    .orElseThrow(() -> new GeneratedException("Post id"));
        } catch (GeneratedException e) {
            throw new RuntimeException(e);
        }

        employee.setCompany_id(employeeRequest.getCompany_id());
        employee.setName(employeeRequest.getName());
        employee.setDepartment_id(employeeRequest.getDepartment_id());

        return employeeRepo.save(employee);
    }



    public String addEmployee(Employee employee) {
        employeeRepo.save(employee);
        return "success";
    }

    public List<Employee> findByname(String name) {
        return employeeRepo.findByname(name);
    }

}
