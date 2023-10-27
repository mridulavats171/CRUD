package com.task.employee.DAO;

import com.task.employee.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Integer> {
    List<Employee> findAll();
//
//    @Query("SELECT e FROM Employees e WHERE e.Company_id = ?1")
//    List<Employee> findByCompany_ID(Integer companyId);
}
