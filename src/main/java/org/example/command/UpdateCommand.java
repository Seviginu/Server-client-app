package org.example.command;

import org.example.collection.MusicBandCollection;
import org.example.collection.builder.MusicBandBuilder;
import org.example.command.exceptions.WrongArgumentException;

import java.util.List;

public class UpdateCommand extends CollectionCommand {

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
    MusicBandBuilder builder =
        new MusicBandBuilder(manager.getChannel(), collection.getElement(id));
    collection.updateElement(id, builder.getElement());
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
