package org.server.cli;

import java.io.IOException;
import java.net.Socket;

import org.server.request.RequestManager;
import org.server.server.Utils;
import request.GetObjectRequest;
import request.Request;
import request.RequestType;
import request.TextRequest;

public class NetworkUserChannel implements UserOutputChannel {
  private final Socket socket;
  private GetObjectRequest<?> request;

  public NetworkUserChannel(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void sendStringLine(String string) {
    Utils.sendMessage(socket, string+"\n", RequestType.OK);
  }

  @Override
  public void sendString(String string) {
    Utils.sendMessage(socket, string, RequestType.OK);
  }

  //  public byte[] get(){
  //    return byteArray;
  //  }

  public void sendResponse(Request<?> response) {
    Utils.sendResponse(socket, response);
  }
}
