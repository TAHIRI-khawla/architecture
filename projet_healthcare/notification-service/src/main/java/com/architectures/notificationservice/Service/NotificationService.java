package com.architectures.notificationservice.Service;

import com.architectures.notificationservice.client.AppointmentClient;
import com.architectures.notificationservice.client.DoctorClient;
import com.architectures.notificationservice.client.PatientClient;
import com.architectures.notificationservice.entity.Notification;
import com.architectures.notificationservice.Repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NotificationService {

    private final NotificationRepository repository;
    private final AppointmentClient appointmentClient;
    private final DoctorClient doctorClient;
    private final PatientClient patientClient;

    public NotificationService(NotificationRepository repository,
                              AppointmentClient appointmentClient,
                              DoctorClient doctorClient,
                              PatientClient patientClient) {
        this.repository = repository;
        this.appointmentClient = appointmentClient;
        this.doctorClient = doctorClient;
        this.patientClient = patientClient;
    }

    public Notification save(Notification notification) {
        // Logique d'envoi d'email/SMS ici
        notification.setSent(false); // Par défaut non envoyé
        
        // Enrichir la notification avec des informations des autres services si nécessaire
        try {
            // Si la notification concerne un rendez-vous, on peut récupérer les détails
            if (notification.getMessage() != null && notification.getMessage().contains("appointment")) {
                Object appointments = appointmentClient.getAllAppointments();
                // Traiter les rendez-vous pour enrichir la notification
            }
            
            // Si la notification concerne un docteur ou un patient, on peut récupérer les détails
            if (notification.getEmail() != null) {
                // Vérifier si c'est un docteur ou un patient
                Object doctors = doctorClient.getAllDoctors();
                Object patients = patientClient.getAllPatients();
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'enrichissement de la notification: " + e.getMessage());
        }
        
        return repository.save(notification);
    }

    public List<Notification> findAll() {
        return repository.findAll();
    }

    public Optional<Notification> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Méthodes pour communiquer avec les autres services
    public Object getAllAppointments() {
        return appointmentClient.getAllAppointments();
    }

    public Object getAppointmentsByDoctor(Long doctorId) {
        return appointmentClient.getAppointmentsByDoctor(doctorId);
    }

    public Object getDoctorById(Long doctorId) {
        return doctorClient.getDoctorById(doctorId);
    }

    public Object getAllDoctors() {
        return doctorClient.getAllDoctors();
    }

    public Object getPatientById(Long patientId) {
        return patientClient.getPatientById(patientId);
    }

    public Object getAllPatients() {
        return patientClient.getAllPatients();
    }
}