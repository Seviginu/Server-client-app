package org.server.command.exceptions;

public class CommandNotFoundException extends Exception {
  public CommandNotFoundException() {
    super("Команда не найдена");
  }
}
