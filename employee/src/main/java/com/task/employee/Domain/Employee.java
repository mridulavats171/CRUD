package com.task.employee.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "company_id",insertable=false, updatable=false)
    private Integer company_id;

    @Column(insertable=false, updatable=false)
    private int department_id;

    public Employee() {
    }

    public Employee(int id, Integer company_id, String name, Integer salary, int department_id) {
        this.id = id;
        this.company_id = company_id;
        this.name = name;
        this.salary = salary;
        this.department_id= department_id;
    }

    public int getId() {
        return id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
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

    @ManyToOne

    private Company company;

    @ManyToOne
    private Department department;

}
