package org.server.commands;

import collection.element.MusicBand;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.server.serverIO.RequestManager;
import utils.CommandNames;

public class PrintDescendingCommand extends CollectionCommand {
  public PrintDescendingCommand(RequestManager requestManager, CommandManager manager) {
    super(requestManager, manager);
  }

  @Override
  public void execute(List<String> args) {
    ArrayList<MusicBand> bands;
    try {
      bands = new ArrayList<>(requestManager.getCollection().getListOfElements());
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
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
    return CommandNames.PRINT_DESCENDING;
  }
}
