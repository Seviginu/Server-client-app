package org.server.command.commands;

import collection.MusicBandCollection;

import java.sql.SQLException;
import java.util.List;

import collection.element.MusicBand;
import org.server.command.CommandManager;
import org.server.command.exceptions.CommandNotFoundException;
import utils.CommandNames;

public class RemoveByIdCommand extends CollectionCommand {

  public RemoveByIdCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  @Override
  public void execute(List<String> args) {
    if (args.isEmpty()) {
      manager.getOutputChannel().sendStringLine("Не указан id");
      return;
    }
    try {
      if (collection.remove(Long.parseLong(args.get(0)))) {
        manager.getOutputChannel().sendStringLine("Элемент успешно удален");
        manager.getCollectionData().removeElement(MusicBand.class,
                Long.parseLong(args.get(0)));
      } else manager.getOutputChannel().sendStringLine("Элемент с указанным id не найден");
    } catch (NumberFormatException e) {
      manager.getOutputChannel().sendStringLine("id должен быть числом");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String getDescription() {
    return "Удаляет элемент по id";
  }

  @Override
  public String getName() {
    return CommandNames.REMOVE_BY_ID;
  }
}
