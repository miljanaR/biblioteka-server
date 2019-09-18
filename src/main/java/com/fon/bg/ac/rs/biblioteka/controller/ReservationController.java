package com.fon.bg.ac.rs.biblioteka.controller;

import com.fon.bg.ac.rs.biblioteka.dto.CreateReservationDto;
import com.fon.bg.ac.rs.biblioteka.entity.Reservation;
import com.fon.bg.ac.rs.biblioteka.exc.NotFoundException;
import com.fon.bg.ac.rs.biblioteka.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ReservationController {

  @Autowired
  ReservationService service;

  @RequestMapping(value = "/api/reservations", method = RequestMethod.GET)
  public ResponseEntity<List<Reservation>> getReservationsByUser(@RequestParam Long id) {
    List<Reservation> list = service.getReservationsByUser(id);
    return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
  }

  @PreAuthorize("hasAnyAuthority('ADMIN')")
  @RequestMapping(value = "/api/reservations/{idOfCopy}",  method = RequestMethod.GET)
  public ResponseEntity<Reservation> getReservationByCopy(@PathVariable("idOfCopy") Long id) throws Exception {
    try {
      Reservation entity = service.getReservationByCopy(id);
      return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    } catch (NotFoundException exc) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation Not Found", exc);
    }
  }

  @RequestMapping(value = "/api/reservations", method = RequestMethod.POST)
  public void saveReservation(@RequestBody CreateReservationDto createReservationDto) throws Exception {
    service.saveReservation(createReservationDto);
  }

@RequestMapping(value = "/api/reservations/today", method = RequestMethod.GET)
  public ResponseEntity<List<Reservation>> getReservationsForToday() {
    List<Reservation> list = service.getReservationsForToday();
    return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
  }
}
