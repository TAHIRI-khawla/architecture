package com.architectures.notificationservice.Service;

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

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public Notification save(Notification notification) {
        // Logique d'envoi d'email/SMS ici
        notification.setSent(false); // Par défaut non envoyé
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
}