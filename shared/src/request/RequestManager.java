package request;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;



public class RequestManager {
  private final ServerSocket channel;

  public RequestManager(ServerSocket channel) {
    this.channel = channel;
  }

  public CommandPackage getRequest() {
    try (Socket socket = channel.accept();
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectStream = new ObjectInputStream(inputStream)) {
      return (CommandPackage) objectStream.readObject();
    } catch (Exception ignored) {
      return null;
    }
  }

  public void sendResponse(Request<?> request) throws IOException {
    try (Socket socket = channel.accept();
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(outputStream)) {
      objectStream.writeObject(request);
    } catch (Exception e) {
      throw e;
    }
  }
}
