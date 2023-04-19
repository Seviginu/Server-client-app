package org.example.cli;

public class IgnoreOutputChannel implements UserOutputChannel {

  @Override
  public void sendStringLine(String string) {}

  @Override
  public void sendString(String string) {}
}
