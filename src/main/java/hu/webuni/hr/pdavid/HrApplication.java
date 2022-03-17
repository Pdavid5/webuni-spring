package hu.webuni.hr.pdavid;

import hu.webuni.hr.pdavid.model.Employee;
import hu.webuni.hr.pdavid.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	SalaryService salaryService;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) {

		Employee emp1 = new Employee(11, "Béla", "alkalmazott", 100, LocalDateTime.of(2015, 5, 4, 0, 0));
		Employee emp2 = new Employee(12, "Aranka", "hr", 200, LocalDateTime.of(2010, 5, 4, 0, 0));
		Employee emp3 = new Employee(21, "János", "karbantartó", 150, LocalDateTime.of(2020, 5, 4, 0, 0));
		Employee emp4 = new Employee(21, "János", "karbantartó", 100, LocalDateTime.of(2018, 5, 4, 0, 0));

		salaryService.newSalary(emp1);
		salaryService.newSalary(emp2);
		salaryService.newSalary(emp3);
		salaryService.newSalary(emp4);

		System.out.println(emp1.getSalary());
		System.out.println(emp2.getSalary());
		System.out.println(emp3.getSalary());
		System.out.println(emp4.getSalary());

	}
}
