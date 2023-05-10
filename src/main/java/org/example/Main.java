package org.example;

import java.io.File;
import java.io.IOException;
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
    commandManager.registerCommand(new ClearCommand(collectionManager, commandManager));
    commandManager.registerCommand(new UpdateCommand(collectionManager, commandManager));
    commandManager.registerCommand(new AddIfMaxCommand(collectionManager, commandManager));
    commandManager.registerCommand(new GroupFrontManCommand(collectionManager, commandManager));
    commandManager.registerCommand(new PrintDescendingCommand(collectionManager, commandManager));
    commandManager.registerCommand(
        new PrintUniqueParticipantsCommand(collectionManager, commandManager));
    commandManager.registerCommand(new RemoveByIdCommand(collectionManager, commandManager));
    commandManager.registerCommand(new RemoveHeadCommand(collectionManager, commandManager));
    commandManager.registerCommand(new InfoCommand(collectionManager, commandManager));
    commandManager.registerCommand(new ExecuteScriptCommand(commandManager));
    UserHandler userHandler = new UserHandler(new ConsoleChannel(), commandManager);
    try {
      userHandler.startLoop();
    } catch (Exception ignore) {
    }



    String aaa = "ss";
  }
}
