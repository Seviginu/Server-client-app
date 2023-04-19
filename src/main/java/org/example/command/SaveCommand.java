package org.example.command;

import java.io.IOException;
import java.util.List;
import org.example.collection.MusicBandCollection;
import org.example.parser.FileManager;

public class SaveCommand extends FileCommand {

  public SaveCommand(MusicBandCollection collection, FileManager file, CommandManager manager) {
    super(collection, file, manager);
  }

  @Override
  public void execute(List<String> args) {
    try {
      file.objToJson(collection);
    } catch (IOException e) {
      System.out.println(e.getMessage());
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
