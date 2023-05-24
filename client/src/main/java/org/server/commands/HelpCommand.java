package org.server.commands;

import utils.CommandNames;

import java.util.List;

public class HelpCommand extends UserCommand {

  public HelpCommand(CommandManager manager) {
    super(manager);
  }

  @Override
  public void execute(List<String> args) {
    for (Command command : manager.getListOfCommands()) {
      manager
          .getOutputChannel()
          .sendStringLine(command.getName() + ": " + command.getDescription());
    }
  }

  @Override
  public String getDescription() {
    return "Выводит список всех команд";
  }

  @Override
  public String getName() {
    return CommandNames.HELP;
  }
}
