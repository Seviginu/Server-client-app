package org.server.command;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.server.collection.MusicBandCollection;
import org.server.collection.element.MusicBand;

public class PrintDescendingCommand extends CollectionCommand {
  public PrintDescendingCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  @Override
  public void execute(List<String> args) {
    ArrayList<MusicBand> bands = new ArrayList<>(collection.getListOfElements());
    bands.sort(Comparator.comparing(MusicBand::getName));
    for (int i = bands.size() - 1; i >= 0; i--) {
      manager
          .getOutputChannel()
          .sendStringLine(
              bands.get(i).toString() + "\n---------------------------------------------------");
    }
  }

  @Override
  public String getDescription() {
    return "Выводит элементы коллекции в порядке убывания(лексикографически по имени группы)";
  }

  @Override
  public String getName() {
    return "print_descending";
  }
}
