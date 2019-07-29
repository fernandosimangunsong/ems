package com.csi.ems.controller;

import com.csi.ems.CompanyRepository;
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
    private CompanyRepository repository;

    @Autowired
    public CompanyController(CompanyRepository companyRepository){
        this.repository = companyRepository;
    }

    @PostMapping(path = "/addCompany")
    @ResponseStatus(HttpStatus.CREATED)
    public @Valid Company addCompany(@Valid @RequestBody Company newCompany){
        return repository.save(newCompany);
    }

    @GetMapping(path = "/profile")
    public ResponseEntity<List<Company>> findCompany() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(path = "/profile/{id}")
    public ResponseEntity<Company> findCompany(@PathVariable Long id) {
        Optional<Company> comppany = repository.findById(id);

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
        if (!repository.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(repository.save(company));
    }



}
