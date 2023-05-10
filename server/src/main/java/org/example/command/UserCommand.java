package org.example.command;

public abstract class UserCommand implements Command {
  CommandManager manager;

  public UserCommand(CommandManager manager) {
    this.manager = manager;
  }
}
