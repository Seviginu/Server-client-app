package org.server.commands.exceptions;

public class WrongArgumentException extends RuntimeException {
  public WrongArgumentException(String message) {
    super(message);
  }
}
