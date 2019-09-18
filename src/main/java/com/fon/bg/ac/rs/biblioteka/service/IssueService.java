package com.fon.bg.ac.rs.biblioteka.service;


import com.fon.bg.ac.rs.biblioteka.dto.CreateLoanDto;
import com.fon.bg.ac.rs.biblioteka.dto.ReturnLoanDto;
import com.fon.bg.ac.rs.biblioteka.entity.Copy;
import com.fon.bg.ac.rs.biblioteka.entity.Issue;
import com.fon.bg.ac.rs.biblioteka.entity.Reservation;
import com.fon.bg.ac.rs.biblioteka.exc.NotFoundException;
import com.fon.bg.ac.rs.biblioteka.repository.IssueRepository;
import com.fon.bg.ac.rs.biblioteka.sendEmail.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class IssueService {

  @Autowired
  private IssueRepository repository;

  @Autowired
  private ReservationService reservationService;

  @Autowired
  private CopyService copyService;

  @Autowired
  private EmailServiceImpl emailService;

  @Transactional
  public void saveLoan(CreateLoanDto zaduzenjeDto) throws Exception {
    Reservation reservation = reservationService.getOne(zaduzenjeDto.getIdRezervacije());
    Issue issue = createZaduzenje(reservation);
    Copy copy = reservation.getPrimerak();
    copy.setZaIzdavanje(false);
    reservation.setOstvarena(true);
    repository.save(issue);
    copyService.savePrimerak(copy);
    reservationService.updateRezervacija(reservation);

  }

  private Issue createZaduzenje(Reservation reservation) {
    Issue issue = new Issue();
    issue.setRezervacija(reservation);
    issue.setDatumZaduzenja(new Date());
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.DATE, 30);
    issue.setRokZaPovratak(c.getTime());
    issue.setVracena(false);
    return issue;

  }

  public void returnLoan(ReturnLoanDto zaduzenjeDto) {
    Issue issue = repository.getOne(zaduzenjeDto.getIdZaduzenje());
    issue.setVracena(true);
    issue.setDatumPovratka(new Date());
    Copy copy = issue.getRezervacija().getPrimerak();
    copy.setZaIzdavanje(true);
    repository.save(issue);
    copyService.savePrimerak(copy);
  }

  public List<Issue> getLoansByUser(Long idKorisnika) {
    List<Issue> zaduzenjaList = repository.findZaduzenjaByUser(idKorisnika);

    if (zaduzenjaList.size() > 0) {
      return zaduzenjaList;
    } else {
      return new ArrayList<>();
    }
  }

  public Issue getLoanByCopy(Long id) throws NotFoundException {
    Issue issue = repository.findZaduzenjeByRezervacijaPrimerakId(id);
    if (issue != null) {
      return issue;
    } else {
      throw new NotFoundException("Issue not found.");
    }
  }

  public void sendZaduzenjaZaOpomenu() {
    List<Issue> zaduzenjaZaOpomenu = repository.getZaduzenjaZaOpomenu();
    if (zaduzenjaZaOpomenu.size() > 0) {
      System.out.println("za opomenu" + zaduzenjaZaOpomenu.get(0).getIdZaduzenje());
      for (Issue issue : zaduzenjaZaOpomenu) {
        emailService.sendEmailOpomena(issue);
      }
    }
  }

  public List<Issue> getHistoryByUser(Long UserId) {
    List<Issue> zaduzenjaList = repository.getHistoryByUser(UserId);

    if (zaduzenjaList.size() > 0) {
      return zaduzenjaList;
    } else {
      return new ArrayList<>();
    }
  }

  public List<Issue> getLoansForToday() {
    List<Issue> zaduzenjaList = repository.getZaduzenjaForToday();

    if (zaduzenjaList.size() > 0) {
      return zaduzenjaList;
    } else {
      return new ArrayList<>();
    }
  }
}
