package hu.webuni.hr.pdavid.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private long id;
    private String name;
    private String adresse;

    private List<EmployeeDto> employeeDtoList;

}
