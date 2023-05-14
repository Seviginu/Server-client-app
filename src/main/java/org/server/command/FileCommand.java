package org.server.command;

import org.server.collection.MusicBandCollection;
import org.server.parser.FileManager;

public abstract class FileCommand extends CollectionCommand {

  protected FileManager file;

  public FileCommand(MusicBandCollection collection, FileManager file, CommandManager manager) {
    super(collection, manager);
    this.file = file;
  }
}
