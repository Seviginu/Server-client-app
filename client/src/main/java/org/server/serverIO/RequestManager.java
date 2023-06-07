package org.server.serverIO;

import collection.MusicBandCollection;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import request.*;
import utils.CommandNames;

/**
 * Class to send requests and receive response from server
 */
public class RequestManager {
  private final InetSocketAddress address;
  private MusicBandCollection cachedCollection;
  private SocketChannel currentSocket;

  /**
   * bind the RequestManager instance with hostname and port
   * @param hostname ip address or hostname
   * @param port server port
   */
  public RequestManager(String hostname, int port) {
    this.address = new InetSocketAddress(hostname, port);
  }

  /**
   * Send command request to server. Does not receive response
   * @param request command and arguments
   * @throws IOException
   */
  public void sendRequest(CommandPackage request) throws IOException {
    try {
      SocketChannel socket = SocketChannel.open();
      this.currentSocket = socket;
      socket.connect(address);
      ObjectOutputStream output = new ObjectOutputStream(socket.socket().getOutputStream());
      output.writeObject(request);
    } catch (IOException e) {
      throw new IOException("Server not available");
    }
  }

  /**
   * Receive update time. Does not send any requests to server
   * @return last time, when collection was updated
   * @throws IOException
   */
  private LocalDateTime receiveUpdateTime() throws IOException {
    try {
      ObjectInputStream input = new ObjectInputStream(this.currentSocket.socket().getInputStream());
      GetObjectRequest<LocalDateTime> response =
          (GetObjectRequest<LocalDateTime>) input.readObject();
      if (response.type() == RequestType.OK) return response.content();
    } catch (ClassNotFoundException ignored) {
    }
    throw new IOException("Can't receive response");
  }

  /**
   * Receive MusicBandCollection. Does not send any requests to server
   * @return actual server collection
   * @throws IOException
   */
  private MusicBandCollection receiveCollection() throws IOException {
    try {
      ObjectInputStream input = new ObjectInputStream(this.currentSocket.socket().getInputStream());
      GetObjectRequest<MusicBandCollection> response =
          (GetObjectRequest<MusicBandCollection>) input.readObject();
      if (response.type() == RequestType.OK) return this.cachedCollection = response.content();
    } catch (ClassNotFoundException ignored) {
    }
    throw new IOException("Can't receive response");
  }

  /**
   * Receive text message from server. Does not send any requests to server
   * @return server text response
   * @throws IOException
   */
  public String receiveMessage() throws IOException {
    try {
      ObjectInputStream input = new ObjectInputStream(this.currentSocket.socket().getInputStream());
      TextRequest response = (TextRequest) input.readObject();
      if (response.type() == RequestType.OK) return response.content();
    } catch (ClassNotFoundException ignored) {
    }
    throw new IOException("Can't receive response");
  }

  /**
   * Send request and instantly get text response from server
   * @param command command to execute
   * @return server response
   * @throws IOException
   */
  public String sendTextRequest(String command) throws IOException {
    sendRequest(new CommandPackage(command, null));
    return receiveMessage();
  }

  /**
   * Send request and instantly get text response from server
   * @param commandPackage command to execute
   * @return server response
   * @throws IOException
   */
  public String sendTextRequest(CommandPackage commandPackage) throws IOException {
    sendRequest(commandPackage);
    return receiveMessage();
  }

  /**
   * Firstly try to compare update times of local(cached) collection and server.
   * If they equals return local collection, otherwise send get collection request and return actual collection.
   * @return collection
   * @throws IOException
   */
  public MusicBandCollection getCollection() throws IOException {
    sendRequest(new CommandPackage(CommandNames.GET_COLLECTION_UPDATE_TIME, null));
    if (cachedCollection != null && cachedCollection.getUpdateTime().equals(receiveUpdateTime()))
      return cachedCollection;
    sendRequest(new CommandPackage(Requests.GET_COLLECTION_REQUEST, null));
    return receiveCollection();
  }
}
