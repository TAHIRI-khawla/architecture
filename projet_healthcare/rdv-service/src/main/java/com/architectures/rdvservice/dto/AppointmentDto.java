package com.architectures.rdvservice.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.architectures.rdvservice.entity.Appointment}
 */
@Value
public class AppointmentDto implements Serializable {
    Long id;
    Long doctorId;
    String patientName;
    String patientPhone;
    LocalDateTime dateTime;
    String status;
}