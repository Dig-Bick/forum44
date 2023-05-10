package com.example.forum4.service;

import com.example.forum4.config.RabbitMQConfig;
import com.example.forum4.entity.Notification;
import com.example.forum4.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @Autowired
    private NotificationService notificationService;

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE)
    public void receiveNotification(Notification notification) {
        // Save the received notification to the database
        notificationService.saveNotification(notification);
        System.out.println("Received notification: " + notification.getMessage());
    }
}
