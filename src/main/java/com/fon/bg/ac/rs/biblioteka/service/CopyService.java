package com.fon.bg.ac.rs.biblioteka.service;


import com.fon.bg.ac.rs.biblioteka.entity.Copy;
import com.fon.bg.ac.rs.biblioteka.exc.NotFoundException;
import com.fon.bg.ac.rs.biblioteka.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CopyService {

  @Autowired
  private CopyRepository repository;

  public Copy getCopyById(Long id) throws Exception {
    Optional<Copy> primerak = repository.findById(id);

    if (primerak.isPresent()) {
      return primerak.get();
    } else {
      throw new NotFoundException("No primerak record exist for given id");
    }
  }

  public List<Copy> getCopiesByIsbn(String publikacija) throws Exception {
    List<Copy> copyList = repository.findByPublikacijaIsbn(publikacija);

    if (copyList.size() > 0) {
      return copyList;
    } else {
      throw new NotFoundException("No primerak record exist for given isbn\"");
    }

  }

  public void savePrimerak(Copy copy) {
    repository.save(copy);
  }

}
