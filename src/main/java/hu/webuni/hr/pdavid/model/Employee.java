package hu.webuni.hr.pdavid.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private long id;
    private String name;
    private String position;
    private int salary;
    private LocalDateTime startOfWork;

}
