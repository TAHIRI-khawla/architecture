package com.architectures.rdvservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.architectures.rdvservice.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorId(Long doctorId);
}