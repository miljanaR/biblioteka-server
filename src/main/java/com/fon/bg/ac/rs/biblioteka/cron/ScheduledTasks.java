package com.fon.bg.ac.rs.biblioteka.cron;

import com.fon.bg.ac.rs.biblioteka.service.ReservationService;
import com.fon.bg.ac.rs.biblioteka.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
  @Autowired
  ReservationService reservationService;

  @Autowired
  IssueService issueService;

  @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
  public void reportCurrentTime() {
    reservationService.getRezervacijeZaPodsetnik();
    reservationService.checkForRemovingRezervacije();
    issueService.sendZaduzenjaZaOpomenu();


  }
}
