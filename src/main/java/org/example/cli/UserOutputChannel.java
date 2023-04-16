package org.example.cli;

public interface UserOutputChannel {
  void sendStringLine(String string);

  void sendString(String string);
}
