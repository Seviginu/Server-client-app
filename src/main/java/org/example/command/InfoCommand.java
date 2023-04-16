package org.example.command;

import org.example.collection.MusicBandCollection;

import java.util.List;

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
    manager.getChannel().sendStringLine(string);
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
