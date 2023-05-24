package org.server.commands;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import org.server.serverIO.RequestManager;
import utils.CommandNames;

public class ClearCommand extends CollectionCommand {
  public ClearCommand(RequestManager requestManager, CommandManager manager) {
    super(requestManager, manager);
  }

  @Override
  public void execute(List<String> args) {
    try {
      requestManager.sendTextRequest(getName());
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @Override
  public String getDescription() {
    return "Удаляет все элементы из коллекции.";
  }

  @Override
  public String getName() {
    return CommandNames.CLEAR;
  }
}
