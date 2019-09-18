package com.fon.bg.ac.rs.biblioteka.controller;

import com.fon.bg.ac.rs.biblioteka.entity.City;
import com.fon.bg.ac.rs.biblioteka.service.CityService;
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
@RequestMapping("/api/cities")
public class CityController {

  @Autowired
  CityService service;

  @GetMapping
  @PreAuthorize("hasAnyAuthority('ADMIN')")
  public ResponseEntity<List<City>> getAllCities() {
    List<City> list = service.getAllCities();

    return new ResponseEntity<List<City>>(list, new HttpHeaders(), HttpStatus.OK);
  }
}
