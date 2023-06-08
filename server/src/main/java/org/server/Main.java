package org.server;


import collection.element.*;
import org.server.database.CollectionData;
import org.server.server.ServerManager;
import java.sql.*;
import java.time.LocalDateTime;

public class Main {
  public static void main(String[] args) throws  SQLException, IllegalAccessException, InstantiationException {

    new ServerManager().startServer(args[0]);
  }
}
