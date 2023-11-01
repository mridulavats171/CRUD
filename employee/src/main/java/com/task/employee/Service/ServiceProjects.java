package com.task.employee.Service;

import com.task.employee.Domain.Projects;
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
        if(repoProjects.existsById(id)){
            repoProjects.deleteById(id);
            return "Deleted";
        }else{
             return "failed";
        }

    }
    public String addProject(Projects projects){
       if(repoProjects.existsById(projects.getId())) {
         return "Duplicate Entry";
       }else{
           repoProjects.save(projects);
           return "Created";

       }
    }
}
