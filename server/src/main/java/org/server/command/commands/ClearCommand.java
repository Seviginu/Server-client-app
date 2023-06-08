package org.server.command.commands;

import collection.MusicBandCollection;

import java.sql.SQLException;
import java.util.List;

import collection.element.MusicBand;
import org.server.command.CommandManager;
import org.server.command.exceptions.CommandNotFoundException;
import utils.CommandNames;

public class ClearCommand extends CollectionCommand {
  public ClearCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  @Override
  public void execute(List<String> args) {
    collection.clear();
    try {
      manager.getCollectionData().clear(MusicBand.class);
    }
    catch (SQLException e){
      e.printStackTrace();
    }
    manager.getOutputChannel().sendStringLine("Коллекция очищена");
  }

  @Override
  public String getDescription() {
    return "Удаляет все элементы из коллекции.";
  }

  @Override
  public String getName() {
    return CommandNames.CLEAR;
  }
}
