package com.task.employee.Controller;

import com.task.employee.Constant.URLMapping;
import com.task.employee.Domain.Department;
import com.task.employee.Domain.Employee;
import com.task.employee.Service.ServiceDepartment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Department Controller")
@RestController
@RequestMapping(URLMapping.DEPARTMENT)

public class DepartmentController {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ServiceDepartment serviceDepartment;
    @Operation(summary = "Get list of all departments")
    @GetMapping
    public List<Department> getALl(){
        return serviceDepartment.getAll().stream().map(department -> modelMapper.map(department,Department.class))
                .collect(Collectors.toList());
    }
    @Operation(summary = "Add a department")
    @PostMapping
    public String addDepartment(Department department){
        return serviceDepartment.addDepartment(department);
    }

    @Operation(summary = "Delete a department")
    @DeleteMapping
    public String deleteDepartment(int id){
        return serviceDepartment.deleteDepartment(id);
    }



}
