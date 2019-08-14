package com.csi.ems;

import com.csi.ems.model.Company;
import com.csi.ems.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeByEmail (String email);
    List<Employee> findEmployeeByCompany(Company id);
}
