package com.fon.bg.ac.rs.biblioteka.service;


import com.fon.bg.ac.rs.biblioteka.entity.Notification;
import com.fon.bg.ac.rs.biblioteka.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

  @Autowired
  private NotificationRepository repository;

  public List<Notification> getAllNotifications() {
    List<Notification> list = repository.findAll();

    if (list.size() > 0) {
      return list;
    } else {
      return new ArrayList<Notification>();
    }
  }

  public void save(Notification notification) {
    repository.save(notification);
  }
}