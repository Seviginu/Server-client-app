package org.example.command;

import org.example.collection.MusicBandCollection;
import org.example.collection.element.MusicBand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PrintDescendingCommand extends CollectionCommand {
  public PrintDescendingCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  @Override
  public void execute(List<String> args) {
    ArrayList<MusicBand> bands = new ArrayList<>(collection.getListOfElements());
    bands.sort(Comparator.comparing(MusicBand::getName));
    for (int i = bands.size() - 1; i >= 0; i--) {
      manager.getChannel().sendString(bands.get(i).toString());
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
