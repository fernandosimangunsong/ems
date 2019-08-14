package com.csi.ems.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "company")
@EntityListeners(AuditingEntityListener.class)
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 65)
    @Column(name = "name_company")
    private String name_company;

    @NotNull
    @Email
    @Size(max = 100)
    @Column(name = "email",unique = true)
    private String email;

    @NotNull
    @Size(max = 100)
    @Column(name = "password")
    private String password;


    @Column(name = "phone_number")
    @Size(max = 12)
    private String contactCompany;


    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dateOfBirth;

    @Size(max = 100)
    @Column(name = "address")
    private String address;

    @Size(max = 100)
    private String country;

    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

}