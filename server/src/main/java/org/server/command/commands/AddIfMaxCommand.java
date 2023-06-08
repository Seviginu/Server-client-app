package org.server.command.commands;

import collection.MusicBandCollection;
import collection.element.MusicBand;
import java.time.LocalDateTime;
import java.util.List;
import org.server.command.CommandManager;
import org.server.command.exceptions.CommandNotFoundException;
import utils.CommandNames;

public class AddIfMaxCommand extends ElementCommand {

  public AddIfMaxCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  private boolean isMax(MusicBand band) {
    boolean isMaximum = true;
    for (MusicBand musicBand : collection.getListOfElements()) {
      if (musicBand.getAlbumsCount() >= band.getAlbumsCount()) {
        isMaximum = false;
        break;
      }
    }
    return isMaximum;
  }

  @Override
  public void execute(List<String> args) {
    if (isMax(element)) {
      element.setId(MusicBandCollection.generateId());
      element.setCreationDate(LocalDateTime.now());
      collection.add(element);
      manager.getOutputChannel().sendStringLine("Элемент добавлен в коллекцию");

      manager.getCollectionData().saveObject(element);

    } else manager.getOutputChannel().sendStringLine("Элемент не добавлен в коллекцию");
  }

  @Override
  public String getDescription() {
    return "Добавляет элемент, если значение albumsCount больше остальных элементов коллекции";
  }

  @Override
  public String getName() {
    return CommandNames.ADD_IF_MAX;
  }
}
