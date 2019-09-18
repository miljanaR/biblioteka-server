package com.fon.bg.ac.rs.biblioteka.exc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsernameExistsException extends Exception {
  public UsernameExistsException(String message) {
    super(message);
  }
}
