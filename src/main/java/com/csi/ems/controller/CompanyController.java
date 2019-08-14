package com.csi.ems.controller;

import com.csi.ems.CompanyRepository;
import com.csi.ems.model.ApiResponse;
import com.csi.ems.model.Company;
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
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
@Slf4j
class CompanyController {
    private CompanyRepository companyRepository;

    @Autowired
    public CompanyController(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }


    @PostMapping("/login")
    public ApiResponse login(@RequestParam  String email, @RequestParam String password) {

        try {
            Company user = companyRepository.findCompanyByEmail(email);
            if(user == null ) {
                return new ApiResponse(400, "User does not exist.", user) ;
            }
            if(!user.getPassword().equals(password)){
                return new ApiResponse(400, "Password mismatch") ;
            }else{
                return new ApiResponse(200, "Login success", user) ;
            }

        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse(400, "Error");
        }
    }

    @PostMapping(path = "/addCompany")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse addCompany(@Valid @RequestBody Company newCompany){
        try {
            companyRepository.save(newCompany);
            return new ApiResponse(200, "Register success", newCompany);
        }catch (Exception e){
             e.printStackTrace();
            return new ApiResponse(400, "Error", newCompany);
        }

    }


    @GetMapping(path = "/profile")
    public ResponseEntity<List<Company>> findCompany() {
        return ResponseEntity.ok(companyRepository.findAll());
    }

    @GetMapping(path = "/profile/{id}")
    public ResponseEntity<Company> findCompany(@PathVariable Long id) {
        Optional<Company> comppany = companyRepository.findById(id);

        if (!comppany.isPresent()) {
            log.error("Id " + id + " is not existed");
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(comppany.get());
        }
    }



    @PutMapping("/edit/{id}")
    public ResponseEntity<Company> update(@PathVariable Long id, @Valid @RequestBody Company company) {
        company.setId(id);
        if (!companyRepository.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
            
        }

        return ResponseEntity.ok(companyRepository.save(company));
    }

    @PutMapping(path = "/editCompany")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse editCompany(@Valid @RequestBody Company updateCompany){
        try {
            companyRepository.save(updateCompany);
            return new ApiResponse(200, "Update success");
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse(400, "Error");
        }

    }



}
