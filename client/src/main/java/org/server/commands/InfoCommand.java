package org.server.commands;

import collection.MusicBandCollection;
import java.io.IOException;
import java.util.List;
import org.server.serverIO.RequestManager;

public class InfoCommand extends CollectionCommand {
  public InfoCommand(RequestManager requestManager, CommandManager manager) {
    super(requestManager, manager);
  }

  @Override
  public void execute(List<String> args) {
    MusicBandCollection collection;
    try {
      collection = requestManager.getCollection();
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    String string =
        "Время создания: "
            + collection.getCreationTime()
            + "\nВремя обновления: "
            + collection.getUpdateTime()
            + "\nКоличество элементов в коллекции: "
            + collection.getListOfElements().size();
    manager.getOutputChannel().sendStringLine(string);
  }

  @Override
  public String getDescription() {
    return "Выводит информацию о коллекции";
  }

  @Override
  public String getName() {
    return "info";
  }
}
