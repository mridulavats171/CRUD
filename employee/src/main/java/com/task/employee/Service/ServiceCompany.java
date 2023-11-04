package com.task.employee.Service;

import com.task.employee.Domain.Company;
import com.task.employee.Repository.RepoCompany;
import com.task.employee.auth.AuthenticationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceCompany {
    @Autowired
    RepoCompany repoCompany;
    public List<Company> getAll() {
      return repoCompany.findAll();
    }

    public String deleteCompany(Integer id) {
        if(repoCompany.existsById(id)){
            repoCompany.deleteById(id);
            return "sucess";
        }else
            return HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase();

    }

    public String addCompany(Company company) {
        repoCompany.save(company);
        return "Sucess";
    }

    public ResponseEntity<Company> updateCompany(Integer id, Company company) {
        Company company1= new Company();
        company1.setCompany_name(company.getCompany_name());

        company1.setLocation(company.getLocation());

        repoCompany.save(company1);
        return ResponseEntity.ok().body(company1);
    }
}
