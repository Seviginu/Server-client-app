package org.server.serverIO;


import collection.MusicBandCollection;
import request.*;
import utils.CommandNames;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;


public class RequestManager {
  private final InetSocketAddress address;
  private MusicBandCollection cachedCollection;
  private SocketChannel currentSocket;

  public RequestManager(String hostname, int port) {
    this.address = new InetSocketAddress(hostname, port);

  }

  public void sendRequest(CommandPackage request) throws IOException {
    try {SocketChannel socket = SocketChannel.open();
      this.currentSocket = socket;
      socket.connect(address);
      ObjectOutputStream output = new ObjectOutputStream(socket.socket().getOutputStream());
      output.writeObject(request);
    }
    catch (IOException e){
      throw new IOException("Server not available");
    }
  }

  private LocalDateTime receiveUpdateTime() throws IOException{
    try  {
      ObjectInputStream input = new ObjectInputStream(this.currentSocket.socket().getInputStream());
      GetObjectRequest<LocalDateTime> response =
              (GetObjectRequest<LocalDateTime>) input.readObject();
      if (response.type() == RequestType.OK) return response.content();
    } catch (ClassNotFoundException ignored) {
    }
    throw new IOException("Can't receive response");
  }

  private MusicBandCollection receiveCollection() throws IOException {
    try  {
      ObjectInputStream input = new ObjectInputStream(this.currentSocket.socket().getInputStream());
      GetObjectRequest<MusicBandCollection> response =
          (GetObjectRequest<MusicBandCollection>) input.readObject();
      if (response.type() == RequestType.OK) return this.cachedCollection = response.content();
    } catch (ClassNotFoundException ignored) {
    }
    throw new IOException("Can't receive response");
  }

  public String receiveMessage() throws IOException {
    try {
      ObjectInputStream input = new ObjectInputStream(this.currentSocket.socket().getInputStream());
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
    sendRequest(new CommandPackage(CommandNames.GET_COLLECTION_UPDATE_TIME, null));
    if(cachedCollection != null && cachedCollection.getUpdateTime().equals(receiveUpdateTime())) return cachedCollection;
    sendRequest(new CommandPackage(Requests.GET_COLLECTION_REQUEST, null));
    return receiveCollection();
  }
}
