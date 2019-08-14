package com.csi.ems.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


import lombok.Data;

@Data
@Entity
@Table(name = "employee")
@EntityListeners(AuditingEntityListener.class)
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 65)
    @Column(name = "first_name")
    private String first_name;

    @Size(max = 65)
    @Column(name = "first_name")
    private String last_name;

    @NotNull
    @Email
    @Size(max = 100)
    @Column(unique = true)
    private String email;

    @ManyToOne
    private Departemen departemen;

    @ManyToOne
    private  Company company;

    @Column(name = "startWork")
    private Date startWork;

    @Column(name="positition")
    private String positition;

    @Column(name = "cv")
    private String cv;

    @Column(name = "photo")
    private String photo;

    @Column(name = "phone_number")
    @Size(max = 15)
    private String phone_number;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dob;

    @Size(max = 100)
    @Column(name = "address")
    private String address;

    @Size(max = 100)
    private String country;

    @Size(max = 100)
    private String status = "AKTIF";


    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();


    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();



}