package com.task.employee.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "department")
public class Department implements Serializable {
    @Id
    private int id;

    private String department_name;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Employee> employee;



}
