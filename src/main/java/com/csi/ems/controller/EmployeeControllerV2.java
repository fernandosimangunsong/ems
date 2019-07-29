package com.csi.ems.controller;

import com.csi.ems.EmployeeProfileRepository;
import com.csi.ems.EmployeeRepository;
import com.csi.ems.ResourceNotFoundException;
import com.csi.ems.model.Employee;
import com.csi.ems.model.EmployeeProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/employee")
@Slf4j
@RequiredArgsConstructor
public class EmployeeControllerV2 {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeProfileRepository employeeProfileRepository;


    @PostMapping(path = "/addEmployee")
    @ResponseBody
    public String addEmployee (@RequestBody Employee employee){

        Employee dataEmployee = employeeRepository.save(employee);

        EmployeeProfile profile =  dataEmployee.getEmployeeProfile();
        profile.setEmployee(dataEmployee);
        employeeProfileRepository.save(profile);

        String status = "success";
        return status;
    }


    @GetMapping(path="/allEmployee")
    public @ResponseBody Iterable<Employee> getAllEmployee() {
        // This returns a JSON or XML with the users
        return employeeRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Employee getById(@PathVariable(value = "id") Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }






}
