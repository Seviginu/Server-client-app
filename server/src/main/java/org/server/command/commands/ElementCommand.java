package org.server.command.commands;

import collection.MusicBandCollection;
import collection.element.MusicBand;
import org.server.command.CommandManager;

public abstract class ElementCommand extends CollectionCommand {

  protected MusicBand element;

  public ElementCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  public void setElement(MusicBand element) {
    this.element = element;
  }
}
