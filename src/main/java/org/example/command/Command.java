package org.example.command;

import java.util.List;

interface Command {
  void execute(List<String> args);

  String getDescription();

  String getName();
}
