package org.example.command.exceptions;

public class WrongArgumentException extends IllegalArgumentException {
  public WrongArgumentException(String message) {
    super(message);
  }
}
