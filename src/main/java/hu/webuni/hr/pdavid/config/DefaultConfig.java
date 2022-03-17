package hu.webuni.hr.pdavid.config;

import hu.webuni.hr.pdavid.service.DefaultEmployeeService;
import hu.webuni.hr.pdavid.service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!smart")
public class DefaultConfig {

    @Bean
    public EmployeeService employeeService(){
        return new DefaultEmployeeService();
    }

}
