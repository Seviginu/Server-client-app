package org.server.commands;

import java.util.List;

public class HistoryCommand extends UserCommand {
  public HistoryCommand(CommandManager manager) {
    super(manager);
  }

  @Override
  public void execute(List<String> args) {
    int counter = 0;
    for (String commandName : manager.getCommandsHistory()) {
      if (counter++ >= 11) return;
      manager.getOutputChannel().sendStringLine(commandName);
    }
  }

  @Override
  public String getDescription() {
    return "Выводит последние 12 команд";
  }

  @Override
  public String getName() {
    return "history";
  }
}
