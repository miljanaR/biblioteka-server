package com.fon.bg.ac.rs.biblioteka.service;


import com.fon.bg.ac.rs.biblioteka.entity.Language;
import com.fon.bg.ac.rs.biblioteka.repository.languageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageService {

  @Autowired
  private languageRepository repository;

  public List<Language> getAllJezik() {
    List<Language> languageList = repository.findAll();

    if (languageList.size() > 0) {
      return languageList;
    } else {
      return new ArrayList<Language>();
    }
  }
}
