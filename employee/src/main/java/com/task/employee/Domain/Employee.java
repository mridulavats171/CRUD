package com.task.employee.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;


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

    @Column(insertable=false, updatable=false)
    private int projects_id;

    public int getProjects_id() {
        return projects_id;
    }

    public void setProjects_id(int projects_id) {
        this.projects_id = projects_id;
    }

    public Employee() {
    }

    public Employee(int id, Integer company_id, String name, Integer salary, int department_id, int projects_id) {
        this.id = id;
        this.company_id = company_id;
        this.name = name;
        this.salary = salary;
        this.department_id= department_id;
        this.projects_id= projects_id;
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST,targetEntity = Projects.class)
    @JoinTable(name = "Employee_Projects_Table",
    joinColumns = {@JoinColumn(name = "employee_id")},inverseJoinColumns = {
            @JoinColumn(name = "projects_id")
    })
    private List<Projects> projects;

}
