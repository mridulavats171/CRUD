package com.task.employee.Controller;

import com.task.employee.Constant.URLMapping;
import com.task.employee.DTO.EmployeeDTO;
import com.task.employee.DTO.ProjectsDTO;
import com.task.employee.Domain.Employee;
import com.task.employee.Domain.Projects;
import com.task.employee.Service.ServiceProjects;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Project Controller")
@RestController
public class ProjectsController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ServiceProjects serviceProjects;
    @Operation(summary = "Get list of all the projects")
    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable Integer id) {
        return serviceProjects.deleteProject(id);}

    @Operation(summary = "list of projects")
    @GetMapping(URLMapping.FINDPROJECTS)
    public List<ProjectsDTO> getProjectinfo() {

        return serviceProjects.findAll().stream().map(projects -> modelMapper.map(projects, ProjectsDTO.class))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Add a new project")
    @PostMapping
    public String addProject( @RequestBody Projects projects)  {
        return serviceProjects.addProject(projects);
    }

}
