package com.fon.bg.ac.rs.biblioteka.controller;

import com.fon.bg.ac.rs.biblioteka.entity.Faculty;
import com.fon.bg.ac.rs.biblioteka.service.FacultyService;
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
@RequestMapping("/api/faculties")
public class FacultyController {

  @Autowired
  FacultyService service;

  @GetMapping
  @PreAuthorize("hasAnyAuthority('ADMIN')")
  public ResponseEntity<List<Faculty>> getAllFakulties() {
    List<Faculty> list = service.getAllFakulties();

    return new ResponseEntity<List<Faculty>>(list, new HttpHeaders(), HttpStatus.OK);
  }
}
