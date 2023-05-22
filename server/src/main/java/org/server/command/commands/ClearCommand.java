package org.server.command.commands;

import collection.MusicBandCollection;
import java.util.List;
import org.server.command.CommandManager;
import org.server.command.exceptions.CommandNotFoundException;

public class ClearCommand extends CollectionCommand {
  public ClearCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  @Override
  public void execute(List<String> args) {
    collection.clear();
    try{
      manager.executeCommand("save");
    }
    catch (CommandNotFoundException ignore){}
    manager.getOutputChannel().sendStringLine("Коллекция очищена");
  }

  @Override
  public String getDescription() {
    return "Удаляет все элементы из коллекции.";
  }

  @Override
  public String getName() {
    return "clear";
  }
}
