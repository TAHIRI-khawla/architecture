package com.architectures.rdvservice.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long doctorId;
    private String patientName;
    private String patientPhone;
    private LocalDateTime dateTime;
    private String status; // CONFIRMED, CANCELED
}
