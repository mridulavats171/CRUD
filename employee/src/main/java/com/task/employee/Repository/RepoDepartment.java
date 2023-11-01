package com.task.employee.Repository;

import com.task.employee.Controller.DepartmentController;
import com.task.employee.Domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoDepartment extends JpaRepository<Department, Integer> {




}
