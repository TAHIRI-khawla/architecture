package com.architectures.notificationservice.controller;




import com.architectures.notificationservice.entity.Notification;
import com.architectures.notificationservice.Service.NotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    public Notification create(@RequestBody Notification notification) {
        return service.save(notification);
    }
}
