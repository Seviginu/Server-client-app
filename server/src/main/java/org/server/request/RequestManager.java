package org.server.request;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.CommandPackage;
import request.Request;

public class RequestManager {

  private final ServerSocket channel;
  private Socket currentSocket;
  private final Logger logger = LogManager.getLogger("org.server.request.RequestManager");

  public RequestManager(ServerSocket channel) {
    this.channel = channel;
  }

  public CommandPackage getRequest() {
    try {
      Socket socket = channel.accept();
      this.currentSocket = socket;
      InputStream inputStream = socket.getInputStream();
      ObjectInputStream objectStream = new ObjectInputStream(inputStream);
      logger.debug("request received");
      CommandPackage result = (CommandPackage) objectStream.readObject();
      logger.trace(result);
      return result;
    } catch (Exception ignored) {
      logger.debug("fail during receive request");
      return null;
    }
  }

  public void sendResponse(Request<?> request) throws IOException {
    try (OutputStream outputStream = this.currentSocket.getOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(outputStream)) {
      objectStream.writeObject(request);
      logger.debug("send response");
      logger.trace(request);
      this.currentSocket.close();
    } catch (Exception e) {
      logger.error("fail during send response");
      logger.trace(e.getStackTrace());
      throw e;
    }
  }
}
