package com.task.employee.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Projects {

    @Id
    private int id;
    private String project_name;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Employee> employeeSet;
}
