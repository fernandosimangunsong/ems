package com.csi.ems.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import lombok.Data;

@Data
@Entity
@Table(name = "departemen")

public class Departemen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_department")
    private String name_department;

    @Column(name = "description")
    private String description;


    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();
}
