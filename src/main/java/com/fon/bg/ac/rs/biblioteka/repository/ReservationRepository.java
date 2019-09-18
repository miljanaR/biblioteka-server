package com.fon.bg.ac.rs.biblioteka.repository;

import com.fon.bg.ac.rs.biblioteka.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  @Query("FROM Reservation where user_id_korisnika=?1 and datum_isteka_rezervacije>=CURRENT_DATE and ostvarena=0 ")
  List<Reservation> findRezervacijaByClanIdKorisnika(Long idKorisnika);

  @Query("FROM Reservation where primerak_id=?1 and ostvarena=0")
  Reservation findRezervacijaByPrimerakId(Long id);

  @Transactional
  @Modifying
  @Query("DELETE FROM Reservation where ostvarena = 0 and datum_isteka_rezervacije < CURRENT_DATE")
  void removeRezervacije();

  @Query(value = "SELECT primerak_id FROM Rezervacija where ostvarena = 0 and datum_isteka_rezervacije < CURRENT_DATE", nativeQuery = true)
  List<Long> getRezervacijeForRemoving();

  @Query(value = "SELECT user_id_korisnika FROM Rezervacija where ostvarena = 0 and datum_isteka_rezervacije < CURRENT_DATE", nativeQuery = true)
  List<Long> getUserIdsForUpdatingNumberOfCancelations();

  @Transactional
  @Modifying
  @Query("UPDATE Copy set za_izdavanje = 1 where id in :primerci")
  void freePrimerci(@Param(("primerci")) List<Long> primerci);

  @Query(value = "SELECT *, TIMESTAMPDIFF(HOUR, CURRENT_TIMESTAMP, datum_isteka_rezervacije) as 'sati'\n" +
      " FROM Rezervacija where ostvarena = 0 and TIMESTAMPDIFF(HOUR, CURRENT_TIMESTAMP, datum_isteka_rezervacije) < 24", nativeQuery = true)
  List<Reservation> getRezervacijeZaPodsetnik();

  @Query("FROM Reservation WHERE ostvarena = 0")
  List<Reservation> findRezervacijeZaDanas();
}
