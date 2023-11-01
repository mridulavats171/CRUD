package com.task.employee.Service;

import com.task.employee.Domain.Company;
import com.task.employee.Repository.RepoCompany;
import org.springframework.beans.factory.annotation.Autowired;
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
        repoCompany.deleteById(id);
        return "sucess";
    }

    public String addCompany(Company company) {
        repoCompany.save(company);
        return "Sucess";
    }
}
