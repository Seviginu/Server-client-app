package org.server.command.commands;

import java.util.List;

import collection.MusicBandCollection;
import org.server.command.CommandManager;

public class RemoveHeadCommand extends CollectionCommand {
  public RemoveHeadCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  @Override
  public void execute(List<String> args) {
    if (collection.getListOfElements().size() == 0)
      manager.getOutputChannel().sendStringLine("В коллекции нет элементов");
    else
      manager
          .getOutputChannel()
          .sendStringLine("Удален элемент с id = " + collection.removeByIndex(0).getId());
  }

  @Override
  public String getDescription() {
    return "Выводит первый элемент в коллекции и удаляет его";
  }

  @Override
  public String getName() {
    return "remove_head";
  }
}
