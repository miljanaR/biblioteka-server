package com.fon.bg.ac.rs.biblioteka.exc;

public class CustomError {

  private String errorMessage;

  public CustomError(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
