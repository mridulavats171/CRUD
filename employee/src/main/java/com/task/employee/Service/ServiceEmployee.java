package com.task.employee.Service;

import com.task.employee.DAO.EmployeeDAO;
import com.task.employee.DTO.EmployeeDTO;
import com.task.employee.Entity.Employee;
import com.task.employee.Exception.GeneratedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmployee {

    @Autowired
    EmployeeDAO employeeDAO;
    public ResponseEntity<List<Employee>> findAll(){
        try{
            return new ResponseEntity<>(employeeDAO.findAll(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public Optional<Employee> findByID(Integer id) {
        return employeeDAO.findById(id);
    }

    public String deleteEmployee(Integer id) {
        try {
            employeeDAO.deleteById(id);
            return "Question deleted successfully.";
        } catch (EmptyResultDataAccessException ex) {
            return "Employee with ID " + id + " not found.";
        }
    }

    public boolean employeeExists(Integer id) {

      return employeeDAO.existsById(id);
    }

    public List<Employee> getAllemp(){
    return employeeDAO.findAll();
}


    public Employee updateEmployee(Integer id, Employee employeeRequest) {
        Employee employee = null;
        try {
            employee = employeeDAO.findById(id)
                    .orElseThrow(() -> new GeneratedException("Post id"));
        } catch (GeneratedException e) {
            throw new RuntimeException(e);
        }

        employee.setCompany_id(employeeRequest.getCompany_id());
        employee.setName(employeeRequest.getName());

        return employeeDAO.save(employee);
    }



//    public String addEmployee(Employee employee) {
//        employeeDAO.save(employee);
//        return "success";
//    }
//    public ResponseEntity<Employee> addEmployee(Employee employee) {
////        Employee employee1 = new Employee();
////        employee1.setCompany_id(employee.getCompany_id());
////        employee1.setSalary(employee.getSalary());
////        employee1.setName(employee.getName());
////        employee1.setId(employee.getId());
////
////        return new ResponseEntity<>(employeeDAO.save(employee1), HttpStatus.CREATED);
////

//    public List<Employee> findByCompany_ID(Integer CompanyId) {
//        Employee employee= new Employee();
//        employee.setCompany_id(CompanyId);
//    }


}
