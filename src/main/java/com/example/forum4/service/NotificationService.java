package com.example.forum4.service;

import com.example.forum4.entity.Notification;
import com.example.forum4.mapper.NotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public void saveNotification(Notification notification) {
        notificationMapper.saveNotification(notification);
    }

    public Long findUserIdByCommentId(Long commentId) {
        return notificationMapper.findUserIdByCommentId(commentId);
    }

    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationMapper.findByUserId(userId);
    }
}
