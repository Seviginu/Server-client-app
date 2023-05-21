package org.server.commands;

public abstract class UserCommand implements Command {
  CommandManager manager;

  public UserCommand(CommandManager manager) {
    this.manager = manager;
  }
}
