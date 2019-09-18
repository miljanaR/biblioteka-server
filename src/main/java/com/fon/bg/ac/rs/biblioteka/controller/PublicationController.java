package com.fon.bg.ac.rs.biblioteka.controller;

import com.fon.bg.ac.rs.biblioteka.entity.Publication;
import com.fon.bg.ac.rs.biblioteka.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {

  @Autowired
  PublicationService service;

  @GetMapping
  public ResponseEntity<List<Publication>> getAllPublications() {
    List<Publication> list = service.getAllPublications();

    return new ResponseEntity<List<Publication>>(list, new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Publication> getPublicationById(@PathVariable("id") String id)
      throws Exception {
    Publication entity = service.getPublicationById(id);

    return new ResponseEntity<Publication>(entity, new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping("/search")
  public ResponseEntity<List<Publication>> getPublicationsByCondition(@RequestParam String autor, @RequestParam String isbn, @RequestParam String nazivPublikacije) {
    List<Publication> list = service.getPublicationsByCondition(autor, isbn, nazivPublikacije);
    return new ResponseEntity<List<Publication>>(list, new HttpHeaders(), HttpStatus.OK);
  }

}

