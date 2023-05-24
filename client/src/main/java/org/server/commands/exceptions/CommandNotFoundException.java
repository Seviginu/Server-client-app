package org.server.commands.exceptions;

public class CommandNotFoundException extends RuntimeException {
  public CommandNotFoundException() {
    super("Команда не найдена");
  }
}
