package org.server.command;

import java.util.List;
import org.server.collection.MusicBandCollection;
import org.server.collection.element.MusicBand;

public class ShowCommand extends CollectionCommand {
  public ShowCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  @Override
  public void execute(List<String> args) {
    for (MusicBand band : collection.getListOfElements()) {
      manager
          .getOutputChannel()
          .sendStringLine(band + "\n---------------------------------------------------");
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
