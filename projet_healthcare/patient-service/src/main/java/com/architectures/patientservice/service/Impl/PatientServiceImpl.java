package com.architectures.patientservice.service.Impl;


import com.architectures.patientservice.client.AppointmentClient;
import com.architectures.patientservice.client.DoctorClient;
import com.architectures.patientservice.client.NotificationClient;
import com.architectures.patientservice.entity.Patient;
import com.architectures.patientservice.repository.PatientRepository;
import com.architectures.patientservice.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PatientServiceImpl implements PatientService {


    private final PatientRepository repository;
    private final AppointmentClient appointmentClient;
    private final DoctorClient doctorClient;
    private final NotificationClient notificationClient;


    public PatientServiceImpl(PatientRepository repository,
                             AppointmentClient appointmentClient,
                             DoctorClient doctorClient,
                             NotificationClient notificationClient) {
        this.repository = repository;
        this.appointmentClient = appointmentClient;
        this.doctorClient = doctorClient;
        this.notificationClient = notificationClient;
    }


    @Override
    public Patient save(Patient patient) {
        Patient saved = repository.save(patient);
        
        // Envoyer une notification lors de la création d'un nouveau patient
        try {
            Map<String, Object> notification = new HashMap<>();
            notification.put("message", "Nouveau patient enregistré: " + patient.getNom());
            notification.put("type", "PATIENT_CREATED");
            notificationClient.createNotification(notification);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de la notification: " + e.getMessage());
        }
        
        return saved;
    }


    @Override
    public Patient getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id " + id));
    }


    @Override
    public List<Patient> getAll() {
        return repository.findAll();
    }


    @Override
    public void delete(Long id) {
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id " + id));
        
        // Vérifier s'il y a des rendez-vous pour ce patient
        try {
            Object appointments = appointmentClient.getAllAppointments();
            // Si des rendez-vous existent, on pourrait empêcher la suppression
            // ou envoyer une notification
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification des rendez-vous: " + e.getMessage());
        }
        
        repository.deleteById(id);
        
        // Envoyer une notification de suppression
        try {
            Map<String, Object> notification = new HashMap<>();
            notification.put("message", "Patient supprimé: " + patient.getNom());
            notification.put("type", "PATIENT_DELETED");
            notificationClient.createNotification(notification);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de la notification: " + e.getMessage());
        }
    }

    // Méthodes pour communiquer avec les autres services
    public Object getAllAppointments() {
        return appointmentClient.getAllAppointments();
    }

    public Object getDoctorById(Long doctorId) {
        return doctorClient.getDoctorById(doctorId);
    }

    public Object getAllDoctors() {
        return doctorClient.getAllDoctors();
    }

    public Object searchDoctors(String specialite) {
        return doctorClient.searchDoctors(specialite);
    }
}