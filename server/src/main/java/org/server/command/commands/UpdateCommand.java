package org.server.command.commands;

import collection.MusicBandCollection;

import java.time.LocalDateTime;
import java.util.List;
import org.server.command.CommandManager;
import org.server.command.exceptions.CommandNotFoundException;
import org.server.command.exceptions.WrongArgumentException;
import utils.CommandNames;

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
    element.setId(id);
    element.setCreationDate(LocalDateTime.now());
    if (collection.updateElement(id, element)) {
      manager.getOutputChannel().sendStringLine("Элемент успешно обновлен");
    } else manager.getOutputChannel().sendStringLine("Элемент с указанным id не существует");
    try{
      manager.executeCommand("save");
    }
    catch (CommandNotFoundException ignore){}
  }

  @Override
  public String getDescription() {
    return "Обновляет элемент с указанным id";
  }

  @Override
  public String getName() {
    return CommandNames.UPDATE;
  }
}
