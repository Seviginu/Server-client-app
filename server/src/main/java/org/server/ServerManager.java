package org.server;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.server.cli.NetworkUserChannel;
import org.server.command.CommandManager;
import org.server.request.RequestManager;
import parser.FileManager;
import request.CommandPackage;

public class ServerManager {
  private static final Logger logger = LogManager.getLogger("org.server.ServerManager");

  public static void startServer() {
    try (ServerSocket server = new ServerSocket()) {
      server.bind(new InetSocketAddress(7566));
      RequestManager manager = new RequestManager(server);
      NetworkUserChannel channel = new NetworkUserChannel(manager);
      CommandManager commandManager = new CommandManager(manager);
      FileManager fileManager =
          new FileManager(
              new File("C:\\Users\\1500k\\IdeaProjects\\untitled1\\server\\collection.json"));
      CommandManager.registerAllCommands(commandManager, fileManager.jsonToObj(), fileManager);

      logger.info("Server ready");

      while (!server.isClosed()) {
        CommandPackage commandPackage = manager.getRequest();
        commandManager.executeCommand(commandPackage);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
