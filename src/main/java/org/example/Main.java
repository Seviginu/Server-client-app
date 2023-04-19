package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import org.example.cli.ConsoleChannel;
import org.example.collection.MusicBandCollection;
import org.example.command.*;
import org.example.parser.FileManager;

public class Main {
  public static void main(String[] args) {
    CommandManager commandManager = new CommandManager(new ConsoleChannel());
    FileManager fileManager = new FileManager(new File("test.json"));
    MusicBandCollection collectionManager = new MusicBandCollection();
    Path path = Paths.get("tesast.json").toAbsolutePath();
    File path1 =
        Paths.get("C:\\Users\\1500k\\IdeaPojectds\\untitled1\\test.sasajson")
            .toAbsolutePath()
            .toFile();
    if (path1.equals(path)) System.out.println("IT'S WORKING~!");
    try {
      collectionManager = fileManager.jsonToObj();

    } catch (IOException e) {
      e.printStackTrace();
    }

    if (collectionManager == null) collectionManager = new MusicBandCollection();

    if (collectionManager.getCreationTime() == null) {
      collectionManager.setCreationTime(LocalDateTime.now());
      collectionManager.setUpdateTime(LocalDateTime.now());
    }
    commandManager.registerCommand(new HelpCommand(commandManager));
    commandManager.registerCommand(new HistoryCommand(commandManager));
    commandManager.registerCommand(new AddCommand(collectionManager, commandManager));
    commandManager.registerCommand(new SaveCommand(collectionManager, fileManager, commandManager));
    commandManager.registerCommand(new ShowCommand(collectionManager, commandManager));
    commandManager.registerCommand(new UpdateCommand(collectionManager, commandManager));
    commandManager.registerCommand(new AddIfMaxCommand(collectionManager, commandManager));
    commandManager.registerCommand(new InfoCommand(collectionManager, commandManager));
    UserHandler userHandler = new UserHandler(new ConsoleChannel(), commandManager);
    userHandler.startLoop();
  }
}
