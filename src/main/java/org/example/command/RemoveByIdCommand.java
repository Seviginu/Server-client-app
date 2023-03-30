package org.example.command;

import org.example.collection.MusicBandCollection;

public class RemoveByIdCommand extends CollectionCommand{


    public RemoveByIdCommand(MusicBandCollection collection) {
        super(collection);
    }

    @Override
    public void execute(String[] args){
        collection.remove(Integer.parseInt(args[0]));
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
