package com.task.employee.Controller;

import com.task.employee.Constant.URLMapping;
import com.task.employee.DTO.EmployeeDTO;
import com.task.employee.Domain.Employee;
import com.task.employee.Exception.GeneratedException;
import com.task.employee.Repository.EmployeeRepo;
import com.task.employee.Service.ServiceEmployee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(URLMapping.EMPLOYEES)
public class EmpController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    ServiceEmployee serviceEmployee;

    public EmpController(ServiceEmployee serviceEmployee) {
        super();
        this.serviceEmployee = serviceEmployee;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return serviceEmployee.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        return serviceEmployee.deleteEmployee(id);
    }

    @GetMapping(URLMapping.FINDEMP)
    public List<EmployeeDTO> getEmployeeinfo() {

        return serviceEmployee.getAllemp().stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

      @PostMapping
      public String addEmployee( @RequestBody Employee employee)  {
        return serviceEmployee.addEmployee(employee);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<List<Employee>> getEmployee(@PathVariable String identifier, @RequestParam(value = "type", required = false) String type) {
        if ("name".equals(type)) {
            return serviceEmployee.getEmpByName(identifier);
        } else if ("id".equals(type)) {
                int id = Integer.parseInt(identifier);
                return serviceEmployee.findEmpById(id);
        } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) throws GeneratedException {
        return serviceEmployee.updateEmployee(id, employee);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO employeeDTO) {
//
//        // convert DTO to Entity
//        Employee employeeRequest = modelMapper.map(employeeDTO, Employee.class);
//
//        Employee employee = serviceEmployee.updateEmployee(id, employeeRequest);
//
//        // entity to DTO
//        EmployeeDTO employeeResponse = modelMapper.map(employee, EmployeeDTO.class);
//
//        return ResponseEntity.ok().body(employeeResponse);
//    }
}

