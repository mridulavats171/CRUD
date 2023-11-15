package com.task.employee.Controller;

import com.task.employee.Constant.URLMapping;
import com.task.employee.DTO.EmployeeDTO;
import com.task.employee.Domain.Employee;
import com.task.employee.Exception.GeneratedException;
import com.task.employee.Repository.EmployeeRepo;
import com.task.employee.Service.ServiceEmployee;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SecurityRequirement(name = "Bearer Authentication")
@OpenAPIDefinition(
        info = @Info(
                title = "Employees",
                version = "0.0",
                description = "My Employee API")
)
@Tag(name = "Employee controller")
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
@Operation(summary = "Get a list of all the employees", description = "Get a list of all the employees")

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return serviceEmployee.findAll();
    }

    @Operation(summary = "Delete an employee", description = "Delete an employee using id")
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        return serviceEmployee.deleteEmployee(id);
    }

    @Operation(summary = "List of employee")
    @GetMapping(URLMapping.FINDEMP)
    public List<EmployeeDTO> getEmployeeinfo() {

        return serviceEmployee.getAllemp().stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Add an employee")
      @PostMapping
      public String addEmployee( @RequestBody Employee employee)  {
        return serviceEmployee.addEmployee(employee);
    }
//
@Operation(summary = "find employee by id")
@GetMapping("/{id}")
    public Employee getById(@PathVariable Integer id){
        return serviceEmployee.findEmpById(id);
    }
//
//    @Tag(name = "List of employees")
//    @Operation(summary = "Search employee using name or id")
//    @GetMapping("/{identifier}")
//    public ResponseEntity<List<Employee>> getEmployee(@PathVariable String identifier, @RequestParam(value = "type", required = false) String type) {
//        if ("name".equals(type)) {
//            return serviceEmployee.getEmpByName(identifier);
//        } else if ("id".equals(type)) {
//                int id = Integer.parseInt(identifier);
//                return serviceEmployee.findEmpById(id);
//        } else {
//                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
//        }
//    }

//    @Operation(summary = "Edit employee using id")
//    @PutMapping("/{id}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) throws GeneratedException {
//        return serviceEmployee.updateEmployee(id, employee);
//    }
    @Operation(summary = "Edit employee using id")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO employeeDTO) {


        Employee employee = serviceEmployee.updatedEmployee(id, employeeDTO);

        // entity to DTO
        EmployeeDTO employeeResponse = modelMapper.map(employee, EmployeeDTO.class);

        return ResponseEntity.ok().body(employeeResponse);
    }


}

