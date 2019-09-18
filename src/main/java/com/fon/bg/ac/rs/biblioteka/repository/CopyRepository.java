package com.fon.bg.ac.rs.biblioteka.repository;

import com.fon.bg.ac.rs.biblioteka.entity.Copy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CopyRepository extends JpaRepository<Copy, Long> {

  List<Copy> findByPublikacijaIsbn(String publikacija);
}
