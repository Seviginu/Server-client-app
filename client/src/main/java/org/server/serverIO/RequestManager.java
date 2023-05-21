package org.server.serverIO;

import collection.MusicBandCollection;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import request.*;

public class RequestManager {
  private final InetSocketAddress address;
  private MusicBandCollection cachedCollection;

  public RequestManager(String hostname, int port) {
    this.address = new InetSocketAddress(hostname, port);
  }

  public void sendRequest(CommandPackage request) throws IOException {
    try (SocketChannel socket = SocketChannel.open()) {
      socket.connect(address);
      ObjectOutputStream output = new ObjectOutputStream(socket.socket().getOutputStream());
      output.writeObject(request);
    }
  }

  public MusicBandCollection receiveCollection() throws IOException {
    try (SocketChannel socket = SocketChannel.open()) {
      socket.connect(address);
      ObjectInputStream input = new ObjectInputStream(socket.socket().getInputStream());
      GetObjectRequest<MusicBandCollection> response =
          (GetObjectRequest<MusicBandCollection>) input.readObject();
      if (response.type() == RequestType.OK) return response.content();
    } catch (ClassNotFoundException ignored) {
    }
    throw new IOException("Can't receive response");
  }

  public String receiveMessage() throws IOException {
    try (SocketChannel socket = SocketChannel.open()) {
      socket.connect(address);
      ObjectInputStream input = new ObjectInputStream(socket.socket().getInputStream());
      TextRequest response = (TextRequest) input.readObject();
      if (response.type() == RequestType.OK) return response.content();
    } catch (ClassNotFoundException ignored) {
    }
    throw new IOException("Can't receive response");
  }

  public String sendTextRequest(String command) throws IOException {
    sendRequest(new CommandPackage(command, null));
    return receiveMessage();
  }

  public String sendTextRequest(CommandPackage commandPackage) throws IOException {
    sendRequest(commandPackage);
    return receiveMessage();
  }

  public MusicBandCollection getCollection() throws IOException {
    sendRequest(new CommandPackage(Requests.GET_COLLECTION_REQUEST, null));
    return receiveCollection();
  }
}
