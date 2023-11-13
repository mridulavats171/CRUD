package com.task.employee.Repository;

import com.task.employee.Domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    List<Employee> findAll();

    List<Employee> name(String name);

    List<Employee> findByname(String name);
    boolean existsById( Integer id);


//
//    @Query("SELECT e FROM Employees e WHERE e.Company_id = ?1")
//    List<Employee> findByCompany_ID(Integer companyId);
}
