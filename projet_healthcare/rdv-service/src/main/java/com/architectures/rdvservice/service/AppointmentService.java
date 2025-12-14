package com.architectures.rdvservice.service;

import com.architectures.rdvservice.client.DoctorClient;
import com.architectures.rdvservice.client.NotificationClient;
import com.architectures.rdvservice.client.PatientClient;
import com.architectures.rdvservice.entity.Appointment;
import com.architectures.rdvservice.repository.AppointmentRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;
    private final DoctorClient doctorClient;
    private final PatientClient patientClient;
    private final NotificationClient notificationClient;

    public AppointmentService(AppointmentRepository repository, 
                             DoctorClient doctorClient,
                             PatientClient patientClient,
                             NotificationClient notificationClient) {
        this.repository = repository;
        this.doctorClient = doctorClient;
        this.patientClient = patientClient;
        this.notificationClient = notificationClient;
    }

    public Appointment create(Appointment appointment) {
        // Vérifier si le docteur existe
        Object doctor = doctorClient.getDoctorById(appointment.getDoctorId());
        if (doctor == null) {
            throw new RuntimeException("Docteur introuvable !");
        }

        // Vérifier si le patient existe (si patientId est fourni)
        // Note: L'entité Appointment utilise patientName et patientPhone
        // On peut vérifier la liste des patients pour validation
        
        appointment.setStatus("CONFIRMED");
        Appointment savedAppointment = repository.save(appointment);

        // Envoyer une notification au patient
        try {
            Map<String, Object> notification = new HashMap<>();
            notification.put("message", "Rendez-vous confirmé pour le " + appointment.getDateTime());
            notification.put("email", appointment.getPatientName() + "@example.com"); // Utiliser email au lieu de recipient
            notification.put("sent", false);
            notificationClient.createNotification(notification);
        } catch (Exception e) {
            // Log l'erreur mais ne bloque pas la création du rendez-vous
            System.err.println("Erreur lors de l'envoi de la notification: " + e.getMessage());
        }

        return savedAppointment;
    }

    public List<Appointment> getByDoctor(Long doctorId) {
        // Vérifier que le docteur existe
        Object doctor = doctorClient.getDoctorById(doctorId);
        if (doctor == null) {
            throw new RuntimeException("Docteur introuvable !");
        }
        return repository.findByDoctorId(doctorId);
    }

    public List<Appointment> getAll() {
        return repository.findAll();
    }
}
