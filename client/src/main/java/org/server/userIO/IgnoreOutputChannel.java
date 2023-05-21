package org.server.userIO;

import IO.UserOutputChannel;

/** Instead of send strings,just do nothing. Can be helpful, when you won't get */
public class IgnoreOutputChannel implements UserOutputChannel {

  /** Do nothing */
  @Override
  public void sendStringLine(String string) {}

  /** Do nothing */
  @Override
  public void sendString(String string) {}
}
