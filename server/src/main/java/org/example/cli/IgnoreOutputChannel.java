package org.example.cli;

/**
 * Instead of send strings,just do nothing. Can be helpful, when you won't get {@link
 * org.example.command.Command} output
 */
public class IgnoreOutputChannel implements UserOutputChannel {

  /** Do nothing */
  @Override
  public void sendStringLine(String string) {}

  /** Do nothing */
  @Override
  public void sendString(String string) {}
}
