package org.server.command.commands;

import collection.MusicBandCollection;
import collection.builder.StringValidator;
import collection.builder.Validators;
import java.io.IOException;
import java.util.List;
import org.server.command.CommandManager;
import org.server.command.exceptions.SaveException;
import parser.FileManager;

public class SaveCommand extends FileCommand {

  public SaveCommand(MusicBandCollection collection, FileManager file, CommandManager manager) {
    super(collection, file, manager);
  }

  @Override
  public void execute(List<String> args) {
    try {
      StringValidator<FileManager> validator = Validators.getFileSaverValidator();
      if (!validator.validate(file)) {
        throw new IOException("Неверно введено значение");
      }
      file.objToJson(collection);

    } catch (IOException e) {
      throw new SaveException("Не удалось сохранить коллекцию", e);
    }
  }

  @Override
  public String getDescription() {
    return "Сохраняет коллекцию в файл";
  }

  @Override
  public String getName() {
    return "save";
  }
}
