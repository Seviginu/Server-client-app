package org.server.command;

import java.util.List;
import org.server.collection.MusicBandCollection;

public class InfoCommand extends CollectionCommand {
  public InfoCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  @Override
  public void execute(List<String> args) {
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
