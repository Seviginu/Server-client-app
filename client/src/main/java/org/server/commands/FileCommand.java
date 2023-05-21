package org.server.commands;

import org.server.serverIO.RequestManager;
import parser.FileManager;

public abstract class FileCommand extends CollectionCommand {

  protected FileManager file;

  public FileCommand(RequestManager requestManager, FileManager file, CommandManager manager) {
    super(requestManager, manager);
    this.file = file;
  }
}
