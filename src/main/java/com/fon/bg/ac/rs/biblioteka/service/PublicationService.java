package com.fon.bg.ac.rs.biblioteka.service;

import com.fon.bg.ac.rs.biblioteka.entity.Publication;
import com.fon.bg.ac.rs.biblioteka.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {

  @Autowired
  private PublicationRepository repository;

  public List<Publication> getAllPublications() {
    List<Publication> publikacijeList = repository.findAll();
    System.out.println("list size " + publikacijeList.size());

    if (publikacijeList.size() > 0) {
      return publikacijeList;
    } else {
      return new ArrayList<Publication>();
    }
  }

  public Publication getPublicationById(String id) throws Exception {
    Optional<Publication> publikacija = repository.findById(id);

    if (publikacija.isPresent()) {
      return publikacija.get();
    } else {
      throw new Exception("No publikacija record exist for given id");
    }
  }

  public void savePublikacija(Publication publication) {
    repository.save(publication);

  }

  public List<Publication> getPublikacijaByAutor(String autor) {
    List<Publication> listPublication = repository.findByAutor(autor);

    if (listPublication.size() > 0) {
      return listPublication;
    } else {
      return new ArrayList<Publication>();
    }
  }

  public List<Publication> getPublicationsByCondition(String autor, String isbn, String nazivPublikacije) {
    System.out.println(autor);
    List<Publication> listPublication = repository.findByIsbnAutorNazivPublikacije(autor, isbn, nazivPublikacije);

    if (listPublication.size() > 0) {
      return listPublication;
    } else {
      return new ArrayList<Publication>();
    }
  }
}
