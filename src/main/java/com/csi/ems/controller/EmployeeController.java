package com.csi.ems.controller;

import com.csi.ems.EmployeeRepository;
import com.csi.ems.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/employee")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @PostMapping(path = "/addEmployee")
    @ResponseStatus(HttpStatus.CREATED)
    public @Valid Employee addEmployee(@Valid @RequestBody Employee newEmployee){
        return  employeeRepository.save(newEmployee);
    }


    @GetMapping(path = "/profile")
    public ResponseEntity<List<Employee>> findAllEmployeeProfile() {
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @GetMapping(path = "/profile/{id}")
    public ResponseEntity<Employee> findByEmployeeById(@PathVariable Long id) {
        Optional<Employee> employeeProfile = employeeRepository.findById(id);

        if (!employeeProfile.isPresent()) {
            log.error("Id " + id + " is not existed");
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(employeeProfile.get());
        }
    }



    @PutMapping("/edit/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        employee.setId(id);
        if (!employeeRepository.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!employeeRepository.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }
        employeeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }




}
