package com.csi.ems;

import com.csi.ems.model.Departemen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartemenRepository extends JpaRepository<Departemen, Long> {
}
