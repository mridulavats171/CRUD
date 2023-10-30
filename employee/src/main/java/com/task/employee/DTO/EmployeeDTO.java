package com.task.employee.DTO;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class EmployeeDTO {


        private int id;
        private String name;
        private Integer company_id;

        private int department_id;

}
