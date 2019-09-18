package com.fon.bg.ac.rs.biblioteka.repository;

import com.fon.bg.ac.rs.biblioteka.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
