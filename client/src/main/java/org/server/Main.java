package org.server;

import org.server.commands.*;
import org.server.serverIO.RequestManager;
import org.server.userIO.ConsoleChannel;
import org.server.userIO.UserHandler;

public class Main {
  public static void main(String[] args) {
    CommandManager commandManager = new CommandManager(new ConsoleChannel());
    RequestManager requestManager = new RequestManager("localhost", 7566);

    commandManager.registerCommand(new HelpCommand(commandManager));
    commandManager.registerCommand(new ClearCommand(requestManager, commandManager));
    commandManager.registerCommand(new GroupFrontManCommand(requestManager, commandManager));
    commandManager.registerCommand(new PrintDescendingCommand(requestManager, commandManager));
    commandManager.registerCommand(
        new PrintUniqueParticipantsCommand(requestManager, commandManager));
    commandManager.registerCommand(new RemoveHeadCommand(requestManager, commandManager));
    commandManager.registerCommand(new RemoveByIdCommand(requestManager, commandManager));
    commandManager.registerCommand(new HistoryCommand(commandManager));
    commandManager.registerCommand(new AddCommand(requestManager, commandManager));
    commandManager.registerCommand(new ShowCommand(requestManager, commandManager));
    commandManager.registerCommand(new UpdateCommand(requestManager, commandManager));
    commandManager.registerCommand(new AddIfMaxCommand(requestManager, commandManager));
    commandManager.registerCommand(new InfoCommand(requestManager, commandManager));
    commandManager.registerCommand(new ExecuteScriptCommand(commandManager));
    UserHandler userHandler = new UserHandler(new ConsoleChannel(), commandManager);
    userHandler.startLoop();
  }
}
