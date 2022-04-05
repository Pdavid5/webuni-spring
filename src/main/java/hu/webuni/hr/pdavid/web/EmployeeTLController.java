package hu.webuni.hr.pdavid.web;

import hu.webuni.hr.pdavid.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
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

    @PostMapping("/employees")
    public String addEmployees(Employee employee){

        employeeList.add(employee);

        return "redirect:employees";
    }

}
