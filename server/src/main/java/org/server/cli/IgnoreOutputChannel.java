package org.server.cli;

import org.server.command.commands.Command;

/**
 * Instead of send strings,just do nothing. Can be helpful, when you won't get {@link
 * Command} output
 */
public class IgnoreOutputChannel implements UserOutputChannel {

  /** Do nothing */
  @Override
  public void sendStringLine(String string) {}

  /** Do nothing */
  @Override
  public void sendString(String string) {}
}
