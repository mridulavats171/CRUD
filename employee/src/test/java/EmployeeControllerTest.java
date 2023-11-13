import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.task.employee.Controller.EmpController;
import com.task.employee.DTO.EmployeeDTO;
import com.task.employee.Domain.Employee;
import com.task.employee.Repository.EmployeeRepo;
import com.task.employee.Service.ServiceEmployee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.task.employee.Domain.Employee.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(EmpController.class)
@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    ObjectWriter objectWriter = objectMapper.writer();

    private static final String END_POINT_PATH = "/api/v1/employees";

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private ServiceEmployee serviceEmployee;

    @InjectMocks
    private EmpController empController;

    Employee record1 = new Employee(33, 2, "Ansh", 18899, 3,4);
    Employee record2 = new Employee(22, 2, "monica", 10099, 3,4);

    Employee record3 = new Employee(15, 3, "anshika", 1327, 3,4);

    @Before
    public void setUp(){
      MockitoAnnotations.openMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(empController).build();
    }

    @Test
    public void getEmployeesTest() throws Exception{
        List<Employee> employees = new ArrayList<>(Arrays.asList(record1,record2,record3));
        ResponseEntity<List<Employee>> responseEntity = ResponseEntity.ok(employees);
        Mockito.when(serviceEmployee.findAll()).thenReturn(responseEntity);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize((3))))
                .andExpect(jsonPath("$[2].name", is("anshika")) )
                .andExpect(jsonPath("$[0].id", is(33)))
                .andExpect(jsonPath("$[0].name", is("Ansh")))
                .andDo(print());
    }

    @Test
    public void addEmployeeTest() throws Exception {
        Employee record = new Employee();
                record.setId(5);
                record.setCompany_id(2);
                record.setName("Heenu");
                record.setSalary(39348);
                record.setDepartment_id(4);
                record.setProjects_id(3);


        Mockito.when(serviceEmployee.addEmployee(record)).thenReturn("Saved");

        String content = objectWriter.writeValueAsString(record);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Saved"));
//                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
////                .andExpect(jsonPath("$.name", is("Heenu")) )
//                .andExpect(jsonPath("$.id", is(5)));

    }

    @Test
    public void deleteEmployeeTest() throws Exception{
        Mockito.when(serviceEmployee.findEmpById(record1.getId())).thenReturn(record1);
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void updateEmployeeTest() throws Exception{
        Employee updateRecord = new Employee();
        updateRecord.setId(record3.getId());
        updateRecord.setProjects_id(4);
        updateRecord.setCompany_id(2);
        updateRecord.setName("tanya");
        updateRecord.setSalary(39348);
        updateRecord.setDepartment_id(4);


        Mockito.when(serviceEmployee.findEmpById(record3.getId())).thenReturn(record3);

        Mockito.when(serviceEmployee.findEmpById(record3.getId())).thenReturn(record3);
        Mockito.when(serviceEmployee.updateEmployee(record3.getId(), updateRecord)).thenReturn("employee updated");

        String updatedRecord = objectWriter.writeValueAsString(updateRecord);

        // Build and perform the request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/v1/employees/edit/3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updatedRecord);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string(""));
    }


//    @Test
//    public void updatedEmployeeTest() throws Exception{
//        EmployeeDTO updateRecord = new EmployeeDTO();
//        updateRecord.setCompany_id(2);
//        updateRecord.setName("tanya");
//        updateRecord.setSalary(39348);
//        updateRecord.setDepartment_id(4);
//
//
//        Mockito.when(serviceEmployee.findEmpById(record3.getId())).thenReturn(record3);
//
//        Mockito.when(serviceEmployee.findEmpById(record3.getId())).thenReturn(record3);
//        Mockito.when(serviceEmployee.updatedEmployee(record3.getId(), updateRecord)).thenReturn(record3);
//
//        String updatedRecord = objectWriter.writeValueAsString(updateRecord);
//
//        // Build and perform the request
//        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/v1/employees/3")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(updatedRecord);
//
//        mockMvc.perform(mockRequest)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is("tanya")));
//    }



}
