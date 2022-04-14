package hu.webuni.hr.pdavid.web;

import hu.webuni.hr.pdavid.dto.CompanyDto;
import hu.webuni.hr.pdavid.dto.EmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/companys")
public class CompanyController {

    //COMPANY

    private Map<Long, CompanyDto> companys = new HashMap<>();

    {
        companys.put(1L, new CompanyDto(1, "Lenovo", "Budapest", new ArrayList<EmployeeDto>()));
        companys.put(2L, new CompanyDto(2, "Asus", "Debrecen", new ArrayList<EmployeeDto>()));
        companys.put(3L, new CompanyDto(3, "Dell", "Szeged", new ArrayList<EmployeeDto>()));
    }

    @GetMapping("/allCompany")
    public List<CompanyDto> allCompany(@RequestParam (required = false) Boolean full){

        if (full == null || !full){
            return companys.values().stream()
                    .map(c -> new CompanyDto(c.getId(), c.getName(), c.getAdresse(), null))
                    .collect(Collectors.toList());
        }

        return new ArrayList<>(companys.values());

    }

    @GetMapping("/company/{id}")
    public ResponseEntity<CompanyDto> companyDtoId(@PathVariable long id){
        CompanyDto companyDto = companys.get(id);

        if (companyDto != null){
            return ResponseEntity.ok(companyDto);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/company/add")
    public CompanyDto addCompany(@RequestBody CompanyDto companyDto){

        companys.put(companyDto.getId(), companyDto);

        return companyDto;
    }

    @DeleteMapping("/del/{id}")
    public void deleteCompany(@PathVariable long id){

        companys.remove(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto){

        if (!companys.containsKey(id)){
            return ResponseEntity.notFound().build();
        }
        else {
            companyDto.setId(id);
            companys.put(id, companyDto);
            return ResponseEntity.ok(companyDto);
        }

    }

    @PostMapping("/{id}/employee")
    public CompanyDto addEmployeeToCompany(@PathVariable long id, @RequestBody EmployeeDto employee){

        CompanyDto companyDto = companys.get(id);

        if (companyDto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else
            companyDto.getEmployeeDtoList().add(employee);

        return companyDto;
    }

    @DeleteMapping("/{companyId}/employee/{employeeId}")
    public CompanyDto deleteEmployeefromCompany(@PathVariable long companyId, @PathVariable long employeeId){

        CompanyDto companyDto = companys.get(companyId);

        companyDto.getEmployeeDtoList().removeIf(employee -> employee.getId() == employeeId);
        return companyDto;
    }

    @PutMapping("/{id}/employee")
    public CompanyDto replaceAllEmployees(@PathVariable long id, @RequestBody List<EmployeeDto> employees){

        CompanyDto companyDto = companys.get(id);
        if (companyDto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else
            companyDto.setEmployeeDtoList(employees);
            return companyDto;
    }

}
