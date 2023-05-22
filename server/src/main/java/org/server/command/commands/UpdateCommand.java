package org.server.command.commands;

import collection.MusicBandCollection;
import java.util.List;
import org.server.command.CommandManager;
import org.server.command.exceptions.WrongArgumentException;

public class UpdateCommand extends ElementCommand {

  public UpdateCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  @Override
  public void execute(List<String> args) {
    if (args.size() == 0) {
      throw new WrongArgumentException("Команда update должна содержать аргумент id");
    }
    long id;
    try {
      id = Long.parseLong(args.get(0));
    } catch (NumberFormatException e) {
      throw new WrongArgumentException("Аргумент id должен быть числом");
    }
    if (collection.updateElement(id, element)) {
      manager.getOutputChannel().sendStringLine("Элемент успешно добавлен");
    } else manager.getOutputChannel().sendStringLine("Элемент с указанным id не существует");
  }

  @Override
  public String getDescription() {
    return "Обновляет элемент с указанным id";
  }

  @Override
  public String getName() {
    return "update";
  }
}
