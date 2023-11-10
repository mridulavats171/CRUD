package com.task.employee.Service;

import com.task.employee.Domain.Projects;
import com.task.employee.Exception.InvalidEntryException;
import com.task.employee.Exception.ProjectNotFoundException;
import com.task.employee.Repository.RepoProjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ServiceProjects {

    @Autowired
    RepoProjects repoProjects;

    public List<Projects> findAll() {
        return repoProjects.findAll();
    }

    public String deleteProject(Integer id) {
        if(!repoProjects.existsById(id)){
            throw new ProjectNotFoundException("Project with  id: " + id + "does not exist");
        }
        repoProjects.deleteById(id);
        return "Deleted";

    }
    public String addProject(Projects projects){
       if(repoProjects.existsById(projects.getId())) {
         throw new InvalidEntryException("Project with given id already exists");
       }else{
           repoProjects.save(projects);
           return "Created";

       }
    }
}
