package com.fon.bg.ac.rs.biblioteka.service;


import com.fon.bg.ac.rs.biblioteka.dto.CreateReservationDto;
import com.fon.bg.ac.rs.biblioteka.entity.Copy;
import com.fon.bg.ac.rs.biblioteka.entity.Reservation;
import com.fon.bg.ac.rs.biblioteka.entity.User;
import com.fon.bg.ac.rs.biblioteka.exc.NotFoundException;
import com.fon.bg.ac.rs.biblioteka.repository.ReservationRepository;
import com.fon.bg.ac.rs.biblioteka.sendEmail.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ReservationService {

  @Autowired
  private ReservationRepository repository;

  @Autowired
  private UserService userService;

  @Autowired
  private CopyService copyService;

  @Autowired
  private EmailServiceImpl emailService;

  public List<Reservation> getReservationsByUser(Long idKorisnika) {
    List<Reservation> reservationList = repository.findRezervacijaByClanIdKorisnika(idKorisnika);

    if (reservationList.size() > 0) {
      return reservationList;
    } else {
      return new ArrayList<>();
    }
  }

  @Transactional
  public void saveReservation(CreateReservationDto rezervacijaDto) throws Exception {
    User user = userService.getUserById(rezervacijaDto.getIdKorisnik());
    System.out.println("clan je " + user.getUsername());
    Copy copy = copyService.getCopyById(rezervacijaDto.getPrimerakId());
    Reservation reservation = createRezervacija(user, copy);
    copy.setZaIzdavanje(false);
    repository.save(reservation);
    copyService.savePrimerak(copy);
  }


  private Reservation createRezervacija(User user, Copy copy) {
    Reservation reservation = new Reservation();
    reservation.setDatumRezervisanja(new Date());
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.DATE, 3);
    reservation.setDatumIstekaRezervacije(c.getTime());
    reservation.setUser(user);
    reservation.setOstvarena(false);
    reservation.setPrimerak(copy);
    return reservation;
  }

  public Reservation getOne(Long idRezervacije) throws Exception {
    Optional<Reservation> rezervacija = repository.findById(idRezervacije);
    if (rezervacija.isPresent()) {
      return rezervacija.get();
    } else {
      throw new Exception("No reservation record exist for given id");
    }
  }

  public void updateRezervacija(Reservation reservation) {
    repository.save(reservation);
  }

  public Reservation getReservationByCopy(Long id) throws NotFoundException {
    Reservation reservation = repository.findRezervacijaByPrimerakId(id);
    if (reservation != null) {
      return reservation;
    } else {
      throw new NotFoundException("reservation not found.");
    }
  }

  public void checkForRemovingRezervacije() {
    System.out.println("checkForRemovingRezervacije");
    List<Long> primerci = repository.getRezervacijeForRemoving();
    if (primerci.size() > 0) {
      removeRezervacije(primerci);
    } else {
      System.out.println("nema niceg za obrisat");
    }
  }

  @Transactional
  private void removeRezervacije(List<Long> primerci) {
    repository.freePrimerci(primerci);
    repository.removeRezervacije();
  }

  public void getRezervacijeZaPodsetnik() {
    List<Reservation> listaRezrvacijaZaPodsetnik = repository.getRezervacijeZaPodsetnik();
    if (listaRezrvacijaZaPodsetnik.size() > 0) {
      System.out.println("za podsetnik" + listaRezrvacijaZaPodsetnik.get(0).getIdRezervacija());
      for (Reservation reservation : listaRezrvacijaZaPodsetnik) {
        emailService.sendEmail(reservation);
      }
    }
  }

  public List<Reservation> getReservationsForToday() {
    List<Reservation> rezervacije = repository.findRezervacijeZaDanas();
    if (rezervacije != null) {
      return rezervacije;
    } else {
      return new ArrayList<>();
    }
  }
}
