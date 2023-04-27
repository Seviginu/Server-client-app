package org.example.command;

import java.util.List;
import org.example.collection.MusicBandCollection;
import org.example.collection.builder.MusicBandBuilder;
import org.example.collection.element.MusicBand;

public class AddIfMaxCommand extends CollectionCommand {

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
    MusicBand band =
        new MusicBandBuilder(
                manager.getInputChannel(),
                manager.getOutputChannel(),
                !args.contains("-nousermode"))
            .getElement();
    if (isMax(band)) {
      collection.add(band);
      manager.getOutputChannel().sendStringLine("Элемент добавлен в коллекцию");
    } else manager.getOutputChannel().sendStringLine("Элемент не добавлен в коллекцию");
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
