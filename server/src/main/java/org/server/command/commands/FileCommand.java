package org.server.command.commands;

import collection.MusicBandCollection;
import org.server.command.CommandManager;
import org.server.database.CollectionData;
import parser.FileManager;

public abstract class FileCommand extends CollectionCommand {

  protected CollectionData collectionData;

  public FileCommand(MusicBandCollection collection, CollectionData file, CommandManager manager) {
    super(collection, manager);
    this.collectionData = file;
  }
}
