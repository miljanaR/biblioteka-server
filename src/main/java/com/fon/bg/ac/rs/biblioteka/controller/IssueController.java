package com.fon.bg.ac.rs.biblioteka.controller;

import com.fon.bg.ac.rs.biblioteka.dto.CreateLoanDto;
import com.fon.bg.ac.rs.biblioteka.dto.ReturnLoanDto;
import com.fon.bg.ac.rs.biblioteka.entity.Issue;
import com.fon.bg.ac.rs.biblioteka.exc.NotFoundException;
import com.fon.bg.ac.rs.biblioteka.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class IssueController {

  @Autowired
  IssueService service;

  @RequestMapping(value = "/api/loans" , method = RequestMethod.GET)
  public ResponseEntity<List<Issue>> getLoansByUser(@RequestParam Long id) {
    List<Issue> list = service.getLoansByUser(id);
    return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
  }

  @RequestMapping(value = "/api/loans/{idOfCopy}", method = RequestMethod.GET)
  @PreAuthorize("hasAnyAuthority('ADMIN')")
  public ResponseEntity<Issue> getLoanByCopy(@PathVariable("idOfCopy") Long id) throws Exception {
    try {
      Issue entity = service.getLoanByCopy(id);
      return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    } catch (NotFoundException exc) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue Not Found", exc);
    }
  }

  @RequestMapping(value = "/api/loans", method = RequestMethod.POST)
  @PreAuthorize("hasAnyAuthority('ADMIN')")
  public void saveLoan(@RequestBody CreateLoanDto createLoanDto) throws Exception {
    service.saveLoan(createLoanDto);
  }

  @RequestMapping(value = "/api/loans", method = RequestMethod.PUT)
  @PreAuthorize("hasAnyAuthority('ADMIN')")
  public void returnLoan(@RequestBody ReturnLoanDto returnLoanDto) throws Exception {
    service.returnLoan(returnLoanDto);
  }

  @RequestMapping(value = "/api/loans/history", method = RequestMethod.GET)
  public ResponseEntity<List<Issue>> getHistoryByUser(@RequestParam Long id) {
    List<Issue> list = service.getHistoryByUser(id);
    return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
  }

  @RequestMapping(value = "/api/loans/today", method = RequestMethod.GET)
  public ResponseEntity<List<Issue>> getLoansForToday() {
    List<Issue> list = service.getLoansForToday();
    return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
  }

}