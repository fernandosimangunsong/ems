package com.csi.ems.controller;

import com.csi.ems.CompanyRepository;
import com.csi.ems.model.Company;
import com.csi.ems.model.LoginApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserLoginController {

    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping("/login")
    public LoginApiResponse login(@RequestParam  String email, @RequestParam String password) {

        try {
            Company user = companyRepository.findCompanyByEmail(email);
            if(user == null ) {
                return new LoginApiResponse(400, "User does not exist.", user) ;
            }
            if(!user.getPassword().equals(password)){
                return new LoginApiResponse(400, "Password mismatch") ;
            }else{
                return new LoginApiResponse(200, "Login success", user) ;
            }

        }catch (Exception e){
            e.printStackTrace();
            return new LoginApiResponse(400, "Error");
        }
    }


}
