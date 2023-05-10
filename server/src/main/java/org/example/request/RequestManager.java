package org.example.request;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.example.collection.element.MusicBand;
import org.example.collection.element.MusicGenre;

public class RequestManager {
  private final ServerSocket channel;

  public RequestManager(ServerSocket channel) {
    this.channel = channel;
  }

  public Request<?> getRequest() {
    try (Socket socket = channel.accept();
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectStream = new ObjectInputStream(inputStream)) {
      return (Request<?>) objectStream.readObject();
    } catch (Exception ignored) {
      return Requests.getErrorRequest("Не удалось считать объект");
    }
  }

  public void sendRequest(Request<?> request) {
    try (Socket socket = channel.accept();
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(outputStream)) {
      objectStream.writeObject(request);
    } catch (Exception ignored) {
    }
  }
}
