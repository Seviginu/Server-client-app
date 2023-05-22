package org.server.command.commands;

import collection.MusicBandCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.server.command.CommandManager;

public abstract class CollectionCommand extends UserCommand {
  protected final Logger logger = LogManager.getLogger("org.server.command.commands");

  protected MusicBandCollection collection;

  public CollectionCommand(MusicBandCollection collection, CommandManager manager) {
    super(manager);
    this.collection = collection;
  }
}
