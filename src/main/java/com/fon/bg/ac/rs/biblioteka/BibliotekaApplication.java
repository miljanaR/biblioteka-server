package com.fon.bg.ac.rs.biblioteka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BibliotekaApplication {

  public static void main(String[] args) {
    SpringApplication.run(BibliotekaApplication.class, args);
  }


}
