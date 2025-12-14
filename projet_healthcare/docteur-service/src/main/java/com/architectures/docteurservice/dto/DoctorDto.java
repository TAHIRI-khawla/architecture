package com.architectures.docteurservice.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.architectures.docteurservice.entity.Doctor}
 */
@Value
public class DoctorDto implements Serializable {
    Long id;
    String nom;
    String specialite;
}