package com.fon.bg.ac.rs.biblioteka.controller;


import com.fon.bg.ac.rs.biblioteka.entity.Notification;
import com.fon.bg.ac.rs.biblioteka.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

  @Autowired
  NotificationService service;

  @GetMapping
  @PreAuthorize("hasAnyAuthority('ADMIN')")
  public ResponseEntity<List<Notification>> getAllNotifications() {
    List<Notification> list = service.getAllNotifications();

    return new ResponseEntity<List<Notification>>(list, new HttpHeaders(), HttpStatus.OK);
  }
}
