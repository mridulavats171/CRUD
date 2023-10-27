package com.task.employee.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;



@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "company_id")
    private Integer company_id;

    public Employee() {
    }

    public Employee(int id, Integer company_id, String name, Integer salary) {
        this.id = id;
        this.company_id = company_id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Column(name = "name")
    private String name;
    @Column(name = "salary")
    private Integer salary;

}
