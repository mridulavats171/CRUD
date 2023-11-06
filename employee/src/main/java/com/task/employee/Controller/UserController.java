package com.task.employee.Controller;

import com.task.employee.Constant.URLMapping;
import com.task.employee.Domain.Users;
import com.task.employee.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(URLMapping.USERS)
public class UserController {

    @Autowired
    UsersRepo usersRepo;
    public List<Users> getAll(){
        return usersRepo.findAll();
    }
}
