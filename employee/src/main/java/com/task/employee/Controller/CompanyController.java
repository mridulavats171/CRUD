package com.task.employee.Controller;

import com.task.employee.Constant.URLMapping;
import com.task.employee.DTO.CompanyDTO;
import com.task.employee.Domain.Company;
import com.task.employee.Domain.Employee;
import com.task.employee.Exception.GeneratedException;
import com.task.employee.Service.ServiceCompany;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(URLMapping.COMPANY)
@Tag(name = "Company Controller")
public class CompanyController {
    @Autowired
    ServiceCompany serviceCompany;
    @Autowired
    private ModelMapper modelMapper;
    @Operation(summary = "Get all companies list")
    @GetMapping
    public List<Company> getAll(){
       return serviceCompany.getAll();
    }

    @Operation(summary = "Delete company")
    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable Integer id) {
        return serviceCompany.deleteCompany(id);
    }

    @Operation(summary = "Company list")
    @GetMapping(URLMapping.FINDEMP)
    public List<CompanyDTO> getCompanyinfo() {

        return serviceCompany.getAll().stream().map(company -> modelMapper.map(company, CompanyDTO.class))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Add company")
    @PostMapping
    public String addCompany( @RequestBody Company company)  {
        return serviceCompany.addCompany(company);
    }

    @Operation(summary = "Edit company using id")
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Integer id, @RequestBody Company company) throws GeneratedException {
        return serviceCompany.updateCompany(id, company);
    }

}
