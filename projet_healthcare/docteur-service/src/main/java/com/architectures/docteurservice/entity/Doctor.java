package com.architectures.docteurservice.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String specialite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}


