package com.csi.ems.controller;

import com.csi.ems.CompanyRepository;
import com.csi.ems.DepartemenRepository;
import com.csi.ems.model.ApiResponse;
import com.csi.ems.model.Company;
import com.csi.ems.model.Departemen;
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
@RequestMapping("/api/v1/departemen")
@RequiredArgsConstructor
@Slf4j
class DepartemenController {
    private DepartemenRepository departemenRepository;

    @Autowired
    public DepartemenController(DepartemenRepository departemenRepository){
        this.departemenRepository = departemenRepository;
    }


    @PostMapping(path = "/addDepartemen")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse addDepartemen(@Valid @RequestBody Departemen newDepartemen){
        try {
            departemenRepository.save(newDepartemen);
            return new ApiResponse(200, "Register success");
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse(400, "Error");
        }

    }


    @GetMapping(path = "/findDepartemen")
    public ResponseEntity<List<Departemen>> findDepartemen() {
        return ResponseEntity.ok(departemenRepository.findAll());
    }

    @GetMapping(path = "/findDepartemen/{id}")
    public ResponseEntity<Departemen> findDepartemen(@PathVariable Long id) {
        Optional<Departemen> departemen = departemenRepository.findById(id);

        if (!departemen.isPresent()) {
            log.error("Id " + id + " is not existed");
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(departemen.get());
        }
    }

}
