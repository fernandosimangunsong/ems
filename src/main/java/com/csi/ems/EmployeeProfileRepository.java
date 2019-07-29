package com.csi.ems;

import com.csi.ems.model.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile,Long> {

}
