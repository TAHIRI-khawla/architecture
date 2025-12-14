package com.architectures.notificationservice.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.architectures.notificationservice.entity.Notification}
 */
@Value
public class NotificationDto implements Serializable {
    Long id;
    String message;
    String email;
    boolean sent;
}