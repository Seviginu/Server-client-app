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

  private void print_element(MusicBand band){
    manager
            .getOutputChannel()
            .sendStringLine(
                    band.toString() + "\n---------------------------------------------------");
  }

  @Override
  public void execute(List<String> args) {
    try {
      requestManager.getCollection().
              getListOfElements().
              stream().
              sorted(Comparator.comparing(MusicBand::getName).reversed()).
              forEach(this::print_element);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
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
