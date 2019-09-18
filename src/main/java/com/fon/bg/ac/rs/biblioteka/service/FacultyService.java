package com.fon.bg.ac.rs.biblioteka.service;


import com.fon.bg.ac.rs.biblioteka.entity.Faculty;
import com.fon.bg.ac.rs.biblioteka.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyService {

  @Autowired
  private FacultyRepository repository;

  public List<Faculty> getAllFakulties() {
    List<Faculty> facultyList = repository.findAll();

    if (facultyList.size() > 0) {
      return facultyList;
    } else {
      return new ArrayList<Faculty>();
    }
  }

  public Faculty findById(Long idFakultet) {
    return repository.getOne(idFakultet);
  }
}