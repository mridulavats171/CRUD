package com.task.employee.Controller;

import com.task.employee.Constant.URLMapping;
import com.task.employee.DTO.CompanyDTO;
import com.task.employee.Domain.Company;
import com.task.employee.Service.ServiceCompany;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(URLMapping.COMPANY)
public class CompanyController {
    @Autowired
    ServiceCompany serviceCompany;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping
    public List<Company> getAll(){
       return serviceCompany.getAll();
    }
    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable Integer id) {
        return serviceCompany.deleteCompany(id);
    }

    @GetMapping(URLMapping.FINDEMP)
    public List<CompanyDTO> getCompanyinfo() {

        return serviceCompany.getAll().stream().map(company -> modelMapper.map(company, CompanyDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String addCompany( @RequestBody Company company)  {
        return serviceCompany.addCompany(company);
    }

}
