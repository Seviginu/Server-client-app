package org.server.command.commands;

import collection.MusicBandCollection;
import org.server.command.CommandManager;
import parser.FileManager;

public abstract class FileCommand extends CollectionCommand {

  protected FileManager file;

  public FileCommand(MusicBandCollection collection, FileManager file, CommandManager manager) {
    super(collection, manager);
    this.file = file;
  }
}
