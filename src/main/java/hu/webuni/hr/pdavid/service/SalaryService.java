package hu.webuni.hr.pdavid.service;

import hu.webuni.hr.pdavid.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    @Autowired
    EmployeeService employeeService;

    public void newSalary(Employee employee){
        employee.setSalary(employeeService.getPayRaisePercent(employee));
    }

}