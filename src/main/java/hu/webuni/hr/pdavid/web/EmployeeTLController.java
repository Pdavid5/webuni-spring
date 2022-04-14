package hu.webuni.hr.pdavid.web;

import hu.webuni.hr.pdavid.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/tl")
public class EmployeeTLController {

    private List<Employee> employeeList = new ArrayList<>();

    {
        employeeList.add(new Employee(1, "Anna", "könyvelő", 2000, LocalDateTime.of(2020, 01,02,14,00)));
        employeeList.add(new Employee(2, "Béla", "szerelő", 1500, LocalDateTime.of(2015, 03,11,6,00)));
        employeeList.add(new Employee(4, "András", "gépkezelő", 1200, LocalDateTime.of(2018, 07,21,8,30)));
    }

    @GetMapping("/employees")
    public String employeeList(Map<String, Object> model){

        model.put("employees", employeeList);
        model.put("newemployee", new Employee());

        return "employees";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String employeeList(@PathVariable long id){

        employeeList.removeIf(employee -> employee.getId() == id);


        return "redirect:/api/tl/employees";
    }

    @PostMapping("/addEmployees")
    public String addEmployees(Employee employee, Map<String, Object> model){

        employeeList.add(employee);

        return "redirect:employees";
    }

    @GetMapping("/employees/{id}")
    public String modifyEmployee(@PathVariable long id, Map<String, Object> model){

        for (Employee emp : employeeList){
            if(emp.getId() == id){
                model.put("editEmployee", emp);
            }
        }

        return "empeditor";
    }

    @GetMapping("/empeditor")
    public String editor(){

        return "empeditor";
    }

    @PostMapping("/employees/{id}")
    public String modifyEmployeeParam(Employee employee){

        for (int i = 0; i < employeeList.size(); i++){

            if (employeeList.get(i).getId() == employee.getId()){
                employeeList.set(i, employee);
                break;
            }

        }

        return "redirect:/api/tl/employees";
    }

}
