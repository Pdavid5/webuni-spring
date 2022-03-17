package hu.webuni.hr.pdavid.service;

import hu.webuni.hr.pdavid.config.HrConfigProperties;
import hu.webuni.hr.pdavid.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class SmartEmployeeService implements EmployeeService{

    @Autowired
    HrConfigProperties config;

    @Override
    public int getPayRaisePercent(Employee employee) {
        LocalDateTime actuallyDate = LocalDateTime.now();
        LocalDateTime employeeWorkStarted = employee.getStartOfWork();

        int yearsInWork = (int) (ChronoUnit.YEARS.between(employeeWorkStarted, actuallyDate));

        int salary = employee.getSalary();
        salary += (salary / 100) * calculatePercent(yearsInWork);

        return salary;
    }

    public int calculatePercent(int yearsInWork){
        int percent = config.getSmart().getPercent().getNothing();

        if (yearsInWork >= config.getSmart().getLimit().getTen()){
            percent = config.getSmart().getPercent().getTen();
        }
        else if(yearsInWork >= config.getSmart().getLimit().getFive() && yearsInWork < config.getSmart().getLimit().getTen()){
            percent = config.getSmart().getPercent().getFive();
        }
        else if(yearsInWork > config.getSmart().getLimit().getTwoandhalf() && yearsInWork < config.getSmart().getLimit().getFive()){
            percent = config.getSmart().getPercent().getTwo();
        }

        return percent;
    }

}
