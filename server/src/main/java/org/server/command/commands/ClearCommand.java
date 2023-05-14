package org.server.command.commands;

import collection.MusicBandCollection;
import org.server.command.CommandManager;

import java.util.List;


public class ClearCommand extends CollectionCommand {
  public ClearCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  @Override
  public void execute(List<String> args) {
    collection.clear();
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
