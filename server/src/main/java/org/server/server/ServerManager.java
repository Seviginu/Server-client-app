package org.server.server;

import collection.MusicBandCollection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import collection.element.MusicBand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.server.command.CommandManager;
import org.server.database.CollectionData;
import org.server.database.UserData;
import org.server.request.RequestManager;
import org.server.user.User;
import parser.FileManager;
import request.*;

public class ServerManager {
  private final Logger logger = LogManager.getLogger("org.server.server.ServerManager");
  private RequestManager requestManager;
  private CommandManager commandManager;
  private FileManager fileManager;
  private MusicBandCollection collection;

  public void startServer(String filename) {
    try (ServerSocket server = new ServerSocket()) {
      server.bind(new InetSocketAddress(7566));
      RequestManager manager = new RequestManager(server);
      this.requestManager = manager;
      File file = new File(filename);
      if(!file.canRead() || !file.canWrite()) throw new IllegalArgumentException("Неверно указан путь до файла");
      FileManager fileManager = new FileManager(file);
      this.fileManager = fileManager;
      CollectionData collectionData = new CollectionData("jdbc:postgresql://localhost:5432/postgres");
      MusicBandCollection collection =
              new MusicBandCollection(collectionData.loadCollection(MusicBand.class));
      this.collection = collection;
      if (collection.getCreationTime() == null) {
        collection.setCreationTime(LocalDateTime.now());
        collection.setUpdateTime(LocalDateTime.now());
      }

      logger.info("Server ready");

      setConnectionLoop(server, collectionData);
    } catch (IOException e) {
      logger.fatal(e);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (InstantiationException e) {
      throw new RuntimeException(e);
    }
  }


  public void setConnectionLoop(ServerSocket server, CollectionData collectionData){
    Properties prop = new Properties();
    try {
      prop.load(new FileInputStream("db.cfg"));
    }
    catch (IOException ignore){}
    UserData userData = new UserData("jdbc:postgresql://localhost:5432/postgres", prop);
    while (!server.isClosed()){
      try {
        Socket socket = server.accept();
        AuthRequest request = Utils.getAuth(socket);
        assert request != null;
        if(request.isNew()) {
          if(userData.Register(request.username(), request.password()))
            Utils.sendMessage(socket, "Регистрация прошла успешно", RequestType.OK);
          else
            Utils.sendMessage(socket, "Имя пользователя уже существует", RequestType.ERROR);
          new SocketThread(socket, collectionData, collection).start();
        }
        else if (userData.Login(request.username(), request.password())) {
          Utils.sendMessage(socket, "Авторизация прошла успешно", RequestType.OK);

          new SocketThread(socket, collectionData, collection).start();
        }
        else Utils.sendMessage(socket, "Неверный логин или пароль", RequestType.ERROR);
      }
      catch (IOException e){
        e.printStackTrace();
      }
    }
  }
}
