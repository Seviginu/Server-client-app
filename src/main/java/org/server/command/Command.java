package org.server.command;

import java.util.List;

/** Interface for user commands */
public interface Command {
  /**
   * execute command with arguments
   *
   * @param args arguments of command
   */
  void execute(List<String> args);

  /**
   * Get description of command action
   *
   * @return description
   */
  String getDescription();

  /**
   * Get command name that user use to execute the command
   *
   * @return command name
   */
  String getName();
}
