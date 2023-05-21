package org.server.commands;

import org.server.serverIO.RequestManager;

public abstract class CollectionCommand extends UserCommand {

  protected RequestManager requestManager;

  public CollectionCommand(RequestManager requestManager, CommandManager manager) {
    super(manager);
    this.requestManager = requestManager;
  }
}
