package org.example.command;

import java.util.List;
import org.example.collection.MusicBandCollection;
import org.example.collection.builder.MusicBandBuilder;
import org.example.collection.element.MusicBand;

public class AddIfMaxCommand extends CollectionCommand {

  public AddIfMaxCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  @Override
  public void execute(List<String> args) {
    MusicBand band = new MusicBandBuilder(manager.getChannel()).getElement();
    boolean isMax = true;
    for (MusicBand musicBand : collection.getListOfElements()) {
      if (musicBand.getAlbumsCount() >= band.getAlbumsCount()) {
        isMax = false;
        break;
      }
    }
    if (isMax) {
      collection.add(band);
      manager.getChannel().sendStringLine("Элемент добавлен в коллекцию");
    } else manager.getChannel().sendStringLine("Элемент не добавлен в коллекцию");
  }

  @Override
  public String getDescription() {
    return "Добавляет элемент, если значение albumsCount больше остальных элементов коллекции";
  }

  @Override
  public String getName() {
    return "add_if_max";
  }
}