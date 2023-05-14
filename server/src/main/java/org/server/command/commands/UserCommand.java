package org.server.command.commands;


import org.server.command.CommandManager;

public abstract class UserCommand implements Command {
  CommandManager manager;

  public UserCommand(CommandManager manager) {
    this.manager = manager;
  }
}
