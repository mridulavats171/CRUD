package com.task.employee.Controller;

import com.task.employee.Constant.URLMapping;
import com.task.employee.DTO.EmployeeDTO;
import com.task.employee.DTO.ProjectsDTO;
import com.task.employee.Domain.Employee;
import com.task.employee.Domain.Projects;
import com.task.employee.Service.ServiceProjects;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProjectsController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ServiceProjects serviceProjects;

    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable Integer id) {
        return serviceProjects.deleteProject(id);}

    @GetMapping(URLMapping.FINDPROJECTS)
    public List<ProjectsDTO> getProjectinfo() {

        return serviceProjects.findAll().stream().map(projects -> modelMapper.map(projects, ProjectsDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String addProject( @RequestBody Projects projects)  {
        return serviceProjects.addProject(projects);
    }


}
