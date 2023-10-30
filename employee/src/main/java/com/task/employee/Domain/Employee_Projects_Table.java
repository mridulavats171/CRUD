package com.task.employee.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employee_projects_table")
@Data
public class Employee_Projects_Table {
    @Id
    private Integer employee_id;
    @Id
    private int projects_id;
}
