package org.server.command.commands;


import collection.MusicBandCollection;
import org.server.command.CommandManager;

public abstract class CollectionCommand extends UserCommand {

  protected MusicBandCollection collection;

  public CollectionCommand(MusicBandCollection collection, CommandManager manager) {
    super(manager);
    this.collection = collection;
  }
}
