package com.fon.bg.ac.rs.biblioteka.repository;

import com.fon.bg.ac.rs.biblioteka.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query("FROM User where username = ?1")
  User findOneByUsername(String username);

  @Query("FROM User where id_korisnika = ?1 and role= 'USER'")
  Optional<User> findUserById(Long id);

  @Query("FROM User where role = 'USER'")
  List<User> findAllUsers();

}
