package com.csi.ems.controller;

import com.csi.ems.EmployeeProfileRepository;
import com.csi.ems.EmployeeRepository;
import com.csi.ems.model.Employee;
import com.csi.ems.model.EmployeeProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.Query;

@RestController
@RequestMapping("/api/v1/employee")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {
    private  EmployeeRepository employeeRepository;

    private  EmployeeProfileRepository employeeProfileRepository;

    @Autowired
    public EmployeeController(EmployeeProfileRepository employeeProfileRepository, EmployeeRepository employeeRepository) {
        this.employeeProfileRepository = employeeProfileRepository;
        this.employeeRepository = employeeRepository;
    }


    @PostMapping(path = "/addEmployee")
    @ResponseBody
    public String addEmployee(@Valid @RequestBody Employee employee){

        Employee dataEmployee = employeeRepository.save(employee);

        EmployeeProfile profile =  dataEmployee.getEmployeeProfile();
        profile.setEmployee(dataEmployee);
        employeeProfileRepository.save(profile);

        return "success";
    }


    @GetMapping(path="/allEmployee")
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employeeRepository.findAll());
    }



    @GetMapping(path = "/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent()) {
            log.error("Id " + id + " is not existed");
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(employee.get());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @Valid @RequestBody Employee employee) {
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
