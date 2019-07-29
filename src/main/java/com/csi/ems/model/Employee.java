package com.csi.ems.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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
    private String firstName;

    @Size(max = 65)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Email
    @Size(max = 100)
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(max = 128)
    private String password;

    @NotNull
    @Column(name = "departemen")
    private String departemen;

    @Column(name = "startWork")
    private Date startWork;

    @Column(name = "cv")
    private String cv;

    @Column(name = "photo")
    private String photo;

    @Column(name = "phone_number")
    @Size(max = 15)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dateOfBirth;

    @Size(max = 100)
    @Column(name = "address1")
    private String address1;

    @Size(max = 100)
    @Column(name = "street")
    private String street;

    @Size(max = 100)
    @Column(name = "city")
    private String city;

    @Size(max = 100)
    @Column(name = "state")
    private String state;

    @Size(max = 100)
    private String country;

    @Size(max = 100)
    private String status = "AKTIF";


    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();


    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "employee_company",
//            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"))
//    private Set<Company> company;


}