package org.example.command;

import org.example.collection.MusicBandCollection;

public abstract class CollectionCommand extends UserCommand {

  protected MusicBandCollection collection;

  public CollectionCommand(MusicBandCollection collection, CommandManager manager) {
    super(manager);
    this.collection = collection;
  }
}
