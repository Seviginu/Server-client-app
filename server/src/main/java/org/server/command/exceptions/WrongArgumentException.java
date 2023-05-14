package org.server.command.exceptions;

public class WrongArgumentException extends IllegalArgumentException {
  public WrongArgumentException(String message) {
    super(message);
  }
}
