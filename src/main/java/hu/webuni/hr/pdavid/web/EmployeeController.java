package hu.webuni.hr.pdavid.web;

import hu.webuni.hr.pdavid.dto.CompanyDto;
import hu.webuni.hr.pdavid.dto.EmployeeDto;
import hu.webuni.hr.pdavid.model.Employee;
import hu.webuni.hr.pdavid.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    private Map<Long, EmployeeDto> employees = new HashMap<>();

    {
        employees.put(1L, new EmployeeDto(1, "Anita", "könyvelő", 2000, LocalDateTime.of(2018, 02, 12,10,00)));
        employees.put(2L, new EmployeeDto(2, "Béla", "szerelő", 1000, LocalDateTime.of(2015, 06, 10,8,00)));
        employees.put(3L, new EmployeeDto(3, "Nándor", "gépkezelő", 1200, LocalDateTime.of(2016, 12, 1,6,40)));
    }

    @GetMapping
    public List<EmployeeDto> getAll(){

        return new ArrayList<>(employees.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable long id){
        EmployeeDto employeeDto = employees.get(id);

        if (employeeDto != null){
            return ResponseEntity.ok(employeeDto);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public EmployeeDto addNewEmployee(@RequestBody EmployeeDto employeeDto){

        employees.put(employeeDto.getId(), employeeDto);
        return employeeDto;
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto){

        if (!employees.containsKey(id)){
            return ResponseEntity.notFound().build();
        }
        else {
            employeeDto.setId(id);
            employees.put(id, employeeDto);
            return ResponseEntity.ok(employeeDto);
        }

    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id){

        employees.remove(id);
    }

    @GetMapping("/salary")
    public ResponseEntity<List<EmployeeDto>> highSalary(@RequestParam("salarylimit") int salaryLimit){
        if (salaryLimit != 0){
            List<EmployeeDto> employeeDtoList = new ArrayList<>();
            for(EmployeeDto employeeDto : employees.values()){
                if (employeeDto.getSalary() > salaryLimit){
                    employeeDtoList.add(employeeDto);
                }
            }
            return ResponseEntity.ok(employeeDtoList);
        }
        else
            return ResponseEntity.notFound().build();

    }

    @PostMapping("/percent")
    public int getPercent(@RequestBody Employee employee){
        return employeeService.getPayRaisePercent(employee);
    }

}
