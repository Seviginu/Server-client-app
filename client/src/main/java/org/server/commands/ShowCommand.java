package org.server.commands;

import collection.element.MusicBand;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import org.server.serverIO.RequestManager;

public class ShowCommand extends CollectionCommand {
  public ShowCommand(RequestManager requestManager, CommandManager manager) {
    super(requestManager, manager);
  }

  @Override
  public void execute(List<String> args) {
    try {
      for (MusicBand band : requestManager.getCollection().getListOfElements()) {
        manager
            .getOutputChannel()
            .sendStringLine(band + "\n---------------------------------------------------");
      }
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @Override
  public String getDescription() {
    return "Выводит строковое представление всех элементов коллекции";
  }

  @Override
  public String getName() {
    return "show";
  }
}
