package com.csi.ems.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
@Entity
@Table(name = "company")
@EntityListeners(AuditingEntityListener.class)
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 65)
    @Column(name = "first_name")
    private String companyName;

    @NotNull
    @Email
    @Size(max = 100)
    @Column(unique = true)
    private String emailCompany;


    @Column(name = "phone_number")
    @Size(max = 15)
    private String contactCompany;


    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dateOfBirth;

    @Size(max = 100)
    @Column(name = "address")
    private String address;

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


    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

//    @ManyToMany(mappedBy = "company")
//    private Set<Employee> employee = new HashSet<>();

//    public Company(@NotNull @Size(max = 65) String companyName, @NotNull @Email @Size(max = 100) String emailCompany, @Size(max = 15) String contactCompany, Date dateOfBirth, @Size(max = 100) String address, @Size(max = 100) String street, @Size(max = 100) String city, @Size(max = 100) String state, @Size(max = 100) String country, Date createdAt, Date updatedAt, Set<Employee> employee) {
//        this.companyName = companyName;
//        this.emailCompany = emailCompany;
//        this.contactCompany = contactCompany;
//        this.dateOfBirth = dateOfBirth;
//        this.address = address;
//        this.street = street;
//        this.city = city;
//        this.state = state;
//        this.country = country;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//        this.employee = employee;
//    }
}