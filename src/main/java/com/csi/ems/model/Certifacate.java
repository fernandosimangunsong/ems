package com.csi.ems.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "certificate")
public class Certifacate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String namaCertificate;

    @Column(nullable = true)
    private String descriptionCertificate;

    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();
}
