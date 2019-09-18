package com.fon.bg.ac.rs.biblioteka.controller;

import com.fon.bg.ac.rs.biblioteka.entity.Copy;
import com.fon.bg.ac.rs.biblioteka.exc.NotFoundException;
import com.fon.bg.ac.rs.biblioteka.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/copies")
public class CopiesController {

  @Autowired
  CopyService service;

  @GetMapping("/{id}")
  public ResponseEntity<Copy> getCopyById(@PathVariable("id") Long id) throws Exception {
    try {
      Copy entity = service.getCopyById(id);
      return new ResponseEntity<Copy>(entity, new HttpHeaders(), HttpStatus.OK);
    } catch (NotFoundException exc) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Copy Not Found", exc);
    }
  }

  @GetMapping()
  public ResponseEntity<List<Copy>> getCopiesByIsbn(@RequestParam String isbn) throws Exception {
    try {
      List<Copy> list = service.getCopiesByIsbn(isbn);
      return new ResponseEntity<List<Copy>>(list, new HttpHeaders(), HttpStatus.OK);
    } catch (NotFoundException exc) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Copy Not Found", exc);
    }

  }



}
