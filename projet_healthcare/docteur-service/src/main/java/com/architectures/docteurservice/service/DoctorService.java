package com.architectures.docteurservice.service;

import com.architectures.docteurservice.client.AppointmentClient;
import com.architectures.docteurservice.client.NotificationClient;
import com.architectures.docteurservice.client.PatientClient;
import com.architectures.docteurservice.entity.Doctor;
import com.architectures.docteurservice.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository repo;
    private final AppointmentClient appointmentClient;
    private final PatientClient patientClient;
    private final NotificationClient notificationClient;

    public DoctorService(DoctorRepository repo,
                        AppointmentClient appointmentClient,
                        PatientClient patientClient,
                        NotificationClient notificationClient) {
        this.repo = repo;
        this.appointmentClient = appointmentClient;
        this.patientClient = patientClient;
        this.notificationClient = notificationClient;
    }

    public List<Doctor> findAll() {
        return repo.findAll();
    }

    public Optional<Doctor> findById(Long id) {
        return repo.findById(id);
    }

    public Doctor save(Doctor doctor) {
        Doctor saved = repo.save(doctor);
        
        // Envoyer une notification lors de la création d'un nouveau docteur
        try {
            Map<String, Object> notification = new HashMap<>();
            notification.put("message", "Nouveau docteur ajouté: " + doctor.getNom());
            notification.put("type", "DOCTOR_CREATED");
            notificationClient.createNotification(notification);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de la notification: " + e.getMessage());
        }
        
        return saved;
    }

    public void delete(Long id) {
        Optional<Doctor> doctor = repo.findById(id);
        if (doctor.isPresent()) {
            // Vérifier s'il y a des rendez-vous pour ce docteur
            try {
                Object appointments = appointmentClient.getAppointmentsByDoctor(id);
                // Si des rendez-vous existent, on pourrait empêcher la suppression
                // ou envoyer une notification
            } catch (Exception e) {
                System.err.println("Erreur lors de la vérification des rendez-vous: " + e.getMessage());
            }
            
            repo.deleteById(id);
            
            // Envoyer une notification de suppression
            try {
                Map<String, Object> notification = new HashMap<>();
                notification.put("message", "Docteur supprimé: " + doctor.get().getNom());
                notification.put("type", "DOCTOR_DELETED");
                notificationClient.createNotification(notification);
            } catch (Exception e) {
                System.err.println("Erreur lors de l'envoi de la notification: " + e.getMessage());
            }
        }
    }

    // ✅ corrigé
    public List<Doctor> findBySpecialite(String specialite) {
        return repo.findBySpecialite(specialite);
    }

    // Méthode pour obtenir les rendez-vous d'un docteur
    public Object getAppointmentsByDoctor(Long doctorId) {
        return appointmentClient.getAppointmentsByDoctor(doctorId);
    }

    // Méthode pour obtenir tous les rendez-vous
    public Object getAllAppointments() {
        return appointmentClient.getAllAppointments();
    }

    // Méthode pour obtenir un patient par ID
    public Object getPatientById(Long patientId) {
        return patientClient.getPatientById(patientId);
    }

    // Méthode pour obtenir tous les patients
    public Object getAllPatients() {
        return patientClient.getAllPatients();
    }
}
