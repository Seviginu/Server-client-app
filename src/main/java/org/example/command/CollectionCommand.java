package org.example.command;

import org.example.collection.MusicBandCollection;

abstract public class CollectionCommand implements Command {

    protected MusicBandCollection collection;

    public CollectionCommand(MusicBandCollection collection){
        this.collection = collection;
    }

}
