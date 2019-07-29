package com.csi.ems.controller;

import com.csi.ems.CompanyRepository;
import com.csi.ems.EmployeeRepository;
import com.csi.ems.model.Employee;
import com.csi.ems.model.LoginApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserLoginController {

    @Autowired
    private EmployeeRepository userService;

    @PostMapping("/login")
    public LoginApiResponse login(@RequestParam  String email, @RequestParam String password){

        Employee user = userService.findEmployeeByEmail(email);
        if(user == null) {
            throw new RuntimeException("User does not exist.");
        }
        if(!user.getPassword().equals(password)){
            throw new RuntimeException("Password mismatch.");
        }
        return new LoginApiResponse(200, "Login success", null) ;
    }


}
