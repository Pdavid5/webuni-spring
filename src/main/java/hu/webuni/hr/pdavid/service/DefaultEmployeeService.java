package hu.webuni.hr.pdavid.service;

import hu.webuni.hr.pdavid.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmployeeService implements EmployeeService {


    @Override
    public int getPayRaisePercent(Employee employee) {

        int salary = employee.getSalary();
        salary += (salary / 100.0) * 5;

        return (int)(salary);
    }
}
