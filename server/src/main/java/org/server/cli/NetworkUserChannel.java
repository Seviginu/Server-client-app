package org.server.cli;

import java.io.IOException;
import org.server.request.RequestManager;
import request.GetObjectRequest;
import request.Request;
import request.RequestType;
import request.TextRequest;

public class NetworkUserChannel implements UserOutputChannel {
  private final RequestManager manager;
  private GetObjectRequest<?> request;

  public NetworkUserChannel(RequestManager manager) {
    this.manager = manager;
  }

  @Override
  public void sendStringLine(String string) {
    try {
      manager.sendResponse(new TextRequest(string + "\n", RequestType.OK));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void sendString(String string) {
    try {
      manager.sendResponse(new TextRequest(string, RequestType.OK));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //  public byte[] get(){
  //    return byteArray;
  //  }

  public void sendResponse(Request<?> response) {
    try {
      manager.sendResponse(response);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
