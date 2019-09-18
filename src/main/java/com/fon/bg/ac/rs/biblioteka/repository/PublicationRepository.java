package com.fon.bg.ac.rs.biblioteka.repository;

import com.fon.bg.ac.rs.biblioteka.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, String> {
  List<Publication> findByAutor(String autor);

  @Query("FROM Publication  WHERE autor LIKE CONCAT('%',:autor,'%') and isbn LIKE CONCAT('%',:isbn,'%') and nazivPublikacije LIKE CONCAT('%',:nazivPublikacije,'%')")
  List<Publication> findByIsbnAutorNazivPublikacije(@Param("autor") String autor, @Param("isbn") String isbn, @Param("nazivPublikacije") String nazivPblikacije);
}
