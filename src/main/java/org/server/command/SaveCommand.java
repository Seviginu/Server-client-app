package org.server.command;

import java.io.IOException;
import java.util.List;
import org.server.cli.ConsoleUserAsker;
import org.server.collection.MusicBandCollection;
import org.server.collection.builder.StringValidator;
import org.server.collection.builder.Validators;
import org.server.command.exceptions.SaveException;
import org.server.parser.FileManager;

public class SaveCommand extends FileCommand {

  public SaveCommand(MusicBandCollection collection, FileManager file, CommandManager manager) {
    super(collection, file, manager);
  }

  @Override
  public void execute(List<String> args) {
    try {
      if (!file.isExist() || !file.writable()) {
        StringValidator<FileManager> validator = Validators.getFileSaverValidator();
        ConsoleUserAsker<FileManager> asker = new ConsoleUserAsker<>();
        FileManager value = asker.askUser(validator, 3);
        if (value == null) {
          throw new IOException("Неверно введено значение");
        }
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
