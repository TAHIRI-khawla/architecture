package com.architectures.rdvservice.service;

import com.architectures.rdvservice.client.DoctorClient;
import com.architectures.rdvservice.entity.Appointment;
import com.architectures.rdvservice.repository.AppointmentRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;
    private final DoctorClient doctorClient;

    public AppointmentService(AppointmentRepository repository, DoctorClient doctorClient) {
        this.repository = repository;
        this.doctorClient = doctorClient;
    }

    public Appointment create(Appointment appointment) {

        // Vérifier si le docteur existe
        Object doctor = doctorClient.getDoctorById(appointment.getDoctorId());

        if (doctor == null) {
            throw new RuntimeException("Docteur introuvable !");
        }

        appointment.setStatus("CONFIRMED");
        return repository.save(appointment);
    }

    public List<Appointment> getByDoctor(Long doctorId) {
        return repository.findByDoctorId(doctorId);
    }

    public List<Appointment> getAll() {
        return repository.findAll();
    }
}
