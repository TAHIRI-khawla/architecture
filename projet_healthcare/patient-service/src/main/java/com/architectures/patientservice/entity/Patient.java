package com.architectures.patientservice.entity;




import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String nom;


    @Column(nullable = false)
    private String prenom;


    @Column(nullable = false, unique = true)
    private String email;


    private String telephone;
}