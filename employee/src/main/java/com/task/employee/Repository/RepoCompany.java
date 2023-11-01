package com.task.employee.Repository;

import com.task.employee.Domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoCompany extends JpaRepository<Company, Integer> {

        List<Company> findAll();

}
