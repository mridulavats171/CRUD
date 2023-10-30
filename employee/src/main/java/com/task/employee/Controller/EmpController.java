package com.task.employee.Controller;

import com.task.employee.Constant.URLMapping;
import com.task.employee.DTO.EmployeeDTO;
import com.task.employee.Domain.Employee;
import com.task.employee.Service.ServiceEmployee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping("/{id}")
    public Optional<Employee> getEmployeeByid(@PathVariable Integer id) {
        return serviceEmployee.findByID(id);
    }


    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        return serviceEmployee.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO employeeDTO) {

        // convert DTO to Entity
        Employee employeeRequest = modelMapper.map(employeeDTO, Employee.class);

        Employee employee = serviceEmployee.updateEmployee(id, employeeRequest);

        // entity to DTO
        EmployeeDTO employeeResponse = modelMapper.map(employee, EmployeeDTO.class);

        return ResponseEntity.ok().body(employeeResponse);
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

    @RequestMapping("/{name}")
    public List<Employee> name(@PathVariable String name) {
        return serviceEmployee.findByname(name);
    }
}

