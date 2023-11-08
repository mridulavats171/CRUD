package com.task.employee.Service;

import com.task.employee.DTO.EmployeeDTO;
import com.task.employee.Repository.EmployeeRepo;
import com.task.employee.Domain.Employee;
import com.task.employee.Exception.GeneratedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    public String addEmployee(Employee employee) {
        employeeRepo.save(employee);
        return "success";
    }

    public Employee updatedEmployee(Integer id, EmployeeDTO employeeRequest) {
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
        employee.setSalary(employeeRequest.getSalary());

        return employeeRepo.save(employee);
    }

//    public ResponseEntity<Employee> updateEmployee(Integer id, Employee employeeRequest) throws GeneratedException {
//        Employee employee = null;
//            employee = employeeRepo.findById(id)
//                    .orElseThrow(() -> new GeneratedException("Post id"));
//
//        employee.setCompany_id(employeeRequest.getCompany_id());
//        employee.setName(employeeRequest.getName());
//        employee.setDepartment_id(employeeRequest.getDepartment_id());
//        employee.setProjects_id(employeeRequest.getProjects_id());
//        employee.setSalary(employeeRequest.getSalary());
//
//         employeeRepo.save(employee);
//         return ResponseEntity.ok().body(employee);
//    }

    public ResponseEntity<List<Employee>> findEmpById(int id) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if (optionalEmployee.isPresent()) {
            return ResponseEntity.ok(Collections.singletonList(optionalEmployee.get()));
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    public ResponseEntity<List<Employee>> getEmpByName(String identifier) {
        List<Employee> employees = employeeRepo.findByname(identifier);
        if (employees.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(employeeRepo.findByname(identifier), HttpStatus.OK);
        }
    }

}
