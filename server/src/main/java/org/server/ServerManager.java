package org.server;

import collection.MusicBandCollection;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.time.LocalDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.server.command.CommandManager;
import org.server.request.RequestManager;
import parser.FileManager;
import request.CommandPackage;

public class ServerManager {
  private static final Logger logger = LogManager.getLogger("org.server.ServerManager");

  public static void startServer(String filename) {
    try (ServerSocket server = new ServerSocket()) {
      server.bind(new InetSocketAddress(7566));
      RequestManager manager = new RequestManager(server);
      CommandManager commandManager = new CommandManager(manager);
      File file = new File(filename);
      if(!file.canRead() || !file.canWrite()) throw new IllegalArgumentException("Неверно указан путь до файла");
      FileManager fileManager = new FileManager(file);
      MusicBandCollection collection = fileManager.jsonToObj();
      if (collection.getCreationTime() == null) {
        collection.setCreationTime(LocalDateTime.now());
        collection.setUpdateTime(LocalDateTime.now());
      }
      CommandManager.registerAllCommands(commandManager, collection, fileManager);

      logger.info("Server ready");

      while (!server.isClosed()) {
        try {
          CommandPackage commandPackage = manager.getRequest();
          commandManager.executeCommand(commandPackage);
        } catch (Exception e) {
          logger.error(e);
          e.printStackTrace();
          commandManager.getOutputChannel().sendStringLine(e.getMessage());
        }
      }
    } catch (IOException e) {
      logger.fatal(e);
    }
  }
}
