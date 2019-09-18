package com.fon.bg.ac.rs.biblioteka.service;


import com.fon.bg.ac.rs.biblioteka.entity.City;
import com.fon.bg.ac.rs.biblioteka.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

  @Autowired
  private CityRepository repository;

  public List<City> getAllCities() {
    List<City> cityList = repository.findAll();

    if (cityList.size() > 0) {
      return cityList;
    } else {
      return new ArrayList<City>();
    }
  }

  public City findById(Long postanskiBroj) {
    return repository.getOne(postanskiBroj);
  }
}
