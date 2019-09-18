package com.fon.bg.ac.rs.biblioteka.repository;

import com.fon.bg.ac.rs.biblioteka.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IssueRepository extends JpaRepository<Issue, Long> {

  @Query(value = "SELECT * FROM Zaduzenje as z join Rezervacija as r on z.id_rezervacija = r.id_rezervacija where r.user_id_korisnika = ?1 and z.datum_povratka is Null", nativeQuery = true)
  List<Issue> findZaduzenjaByUser(Long idKorisnika);

  @Query(value = "SELECT * FROM Zaduzenje as z join Rezervacija as r on z.id_rezervacija = r.id_rezervacija where r.primerak_id = ?1 and z.datum_povratka is Null", nativeQuery = true)
  Issue findZaduzenjeByRezervacijaPrimerakId(Long id);

  @Query(value = "SELECT * from Zaduzenje where vracena = 0  and TIMESTAMPDIFF(HOUR, CURRENT_TIMESTAMP, rok_za_povratak) > 24 and TIMESTAMPDIFF(HOUR, CURRENT_TIMESTAMP, rok_za_povratak) < 48", nativeQuery = true)
  List<Issue> getZaduzenjaZaOpomenu();

  @Query(value = "SELECT * FROM Zaduzenje as z join Rezervacija as r on z.id_rezervacija = r.id_rezervacija where r.user_id_korisnika = ?1 and z.datum_povratka is Not Null", nativeQuery = true)
  List<Issue> getHistoryByUser(Long userId);

  @Query("FROM Issue WHERE vracena = 0 ORDER BY rok_za_povratak")
  List<Issue> getZaduzenjaForToday();
}
