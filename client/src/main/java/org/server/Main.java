package org.server;

import java.io.IOException;
import java.net.Socket;

public class Main {
  public static void main(String[] args) throws IOException {
    System.out.println("Hello world!");
    Socket socket = new Socket("localhost", 7566);
    socket.getOutputStream().write(1);
    socket.close();
    socket = new Socket("localhost", 7566);
    System.out.println(socket.getInputStream().read());
    socket.close();
  }
}
