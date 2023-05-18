package com.example.forum4.mapper;

import com.example.forum4.entity.Notification;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NotificationMapper {

    void saveNotification(Notification notification);

    Long findUserIdByCommentId(Long commentId);

    List<Notification> findByUserId(Long userId);

@Delete("DELETE FROM notifications WHERE  id = #{id}")
    void deleteNotification(Long id);

}
