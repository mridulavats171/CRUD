package com.task.employee.Repository;

import com.task.employee.Domain.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoProjects extends JpaRepository<Projects,Integer> {

}
