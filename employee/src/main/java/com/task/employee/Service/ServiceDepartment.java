package com.task.employee.Service;

import com.task.employee.Constant.URLMapping;
import com.task.employee.Domain.Department;
import com.task.employee.Domain.Employee;
import com.task.employee.Repository.RepoDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceDepartment {

    @Autowired
    RepoDepartment repoDepartment;

    public List<Department> getAll() {
        return repoDepartment.findAll();
    }

    public String addDepartment(Department department) {
        if(repoDepartment.existsById(department.getId())){
            return "ID already exists";
        }else{
            repoDepartment.save(department);
            return "Saved Department";
        }
    }

    public String deleteDepartment(int id) {
        if(repoDepartment.existsById(id)){
            repoDepartment.deleteById(id);
            return HttpStatus.OK.getReasonPhrase();
        }else{
         return HttpStatus.BAD_REQUEST.getReasonPhrase();
        }
    }

//    public ResponseEntity<List<Department>> findByID(int id) {
//        Optional<Department> optionalDepartment = repoDepartment.findById(id);
//        return optionalDepartment.map(department -> ResponseEntity.ok(Collections.singletonList(department))).orElseGet(() -> ResponseEntity.notFound().build());
//    }

//    public String addDepartment(Department department) {
//        if(repoDepartment.existsById(department.getId())){
//            return "ID already exists";
//        }else{
//            repoDepartment.save(department);
//            return "Added department successfully";
//        }
//    }
}

