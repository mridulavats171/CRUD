package com.task.employee;

import com.task.employee.DTO.EmployeeDTO;
import com.task.employee.Entity.Employee;
import com.task.employee.Exception.GeneratedException;
import com.task.employee.Service.ServiceEmployee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employees")
public class empcontroller{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    ServiceEmployee serviceEmployee;

    public empcontroller(ServiceEmployee serviceEmployee) {
        super();
        this.serviceEmployee = serviceEmployee;
    }
    @GetMapping("findAll")
    public ResponseEntity<List<Employee>> getEmployees(){
        return serviceEmployee.findAll();
    }

    @RequestMapping("id/{id}")
    public Optional<Employee> getEmployeeByid(@PathVariable Integer id){
        return serviceEmployee.findByID(id);
    }


    @DeleteMapping("delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        System.out.print("ID FROM FRONTEND"+ id);
        if (serviceEmployee.employeeExists(id)) {
            serviceEmployee.deleteEmployee(id);
            return "Employee deleted successfully.";
        } else {
            return "failed";
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO employeeDTO) {

        // convert DTO to Entity
        Employee employeeRequest = modelMapper.map(employeeDTO, Employee.class);

        Employee employee = serviceEmployee.updateEmployee(id, employeeRequest);

        // entity to DTO
        EmployeeDTO employeeResponse = modelMapper.map(employee, EmployeeDTO.class);

        return ResponseEntity.ok().body(employeeResponse);
    }

//    @PostMapping("/employee")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<Employee> addEmployee( @RequestBody Employee employee) throws GeneratedException {
//        Employee employee1 = serviceEmployee.addEmployee(employee).getBody();
//       if(employee1==null){
//           throw new GeneratedException("");
//    } else return new ResponseEntity<>(employee1, HttpStatus.CREATED);
//    }
//      @PostMapping("/employee")
//      public String addEmployee( @RequestBody Employee employee)  {
//        return serviceEmployee.addEmployee(employee);
//
//    }

//    @RequestMapping("company/{company_id}")
//    public List<Employee> findByCompanyID(@PathVariable Integer Company_id){
//        return serviceEmployee.findByCompany_ID(Company_id);
//    }

}
