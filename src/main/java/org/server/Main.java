package org.server;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import org.server.cli.ConsoleChannel;
import org.server.collection.MusicBandCollection;
import org.server.command.*;
import org.server.parser.FileManager;

public class Main {
  public static void main(String[] args) {
    CommandManager commandManager = new CommandManager(new ConsoleChannel());
    File file;
    if (args.length == 0) file = new File("test.json");
    else file = new File(args[0]);
    FileManager fileManager = new FileManager(file);
    MusicBandCollection collectionManager = null;
    try {
      collectionManager = fileManager.jsonToObj();

    } catch (IOException e) {
      System.out.println("Не удалось загрузить файл");;
    }
    if (collectionManager == null) collectionManager = new MusicBandCollection();

    System.out.println("Удалено невалидных елементов коллекции: " + collectionManager.removeUnvalidElems());

    if (collectionManager.getCreationTime() == null) {
      collectionManager.setCreationTime(LocalDateTime.now());
      collectionManager.setUpdateTime(LocalDateTime.now());
    }
    commandManager.registerCommand(new HelpCommand(commandManager));
    commandManager.registerCommand(new ClearCommand(collectionManager, commandManager));
    commandManager.registerCommand(new GroupFrontManCommand(collectionManager, commandManager));
    commandManager.registerCommand(new PrintDescendingCommand(collectionManager, commandManager));
    commandManager.registerCommand(new PrintUniqueParticipantsCommand(collectionManager, commandManager));
    commandManager.registerCommand(new RemoveHeadCommand(collectionManager, commandManager));
    commandManager.registerCommand(new RemoveByIdCommand(collectionManager, commandManager));
    commandManager.registerCommand(new HistoryCommand(commandManager));
    commandManager.registerCommand(new AddCommand(collectionManager, commandManager));
    commandManager.registerCommand(new SaveCommand(collectionManager, fileManager, commandManager));
    commandManager.registerCommand(new ShowCommand(collectionManager, commandManager));
    commandManager.registerCommand(new UpdateCommand(collectionManager, commandManager));
    commandManager.registerCommand(new AddIfMaxCommand(collectionManager, commandManager));
    commandManager.registerCommand(new InfoCommand(collectionManager, commandManager));
    commandManager.registerCommand(new ExecuteScriptCommand(commandManager));
    UserHandler userHandler = new UserHandler(new ConsoleChannel(), commandManager);
    userHandler.startLoop();
  }
}
