package com.architectures.docteurservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "${notification.service.url}")
public interface NotificationClient {
    @PostMapping("/notifications")
    Object createNotification(@RequestBody Object notification);
}

