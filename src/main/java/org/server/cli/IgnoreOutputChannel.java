package org.server.cli;

/**
 * Instead of send strings,just do nothing. Can be helpful, when you won't get {@link
 * org.server.command.Command} output
 */
public class IgnoreOutputChannel implements UserOutputChannel {

  /** Do nothing */
  @Override
  public void sendStringLine(String string) {}

  /** Do nothing */
  @Override
  public void sendString(String string) {}
}
