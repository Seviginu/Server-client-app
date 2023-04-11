package org.example.command;

import org.example.collection.MusicBandCollection;

import java.util.List;

public class RemoveByIdCommand extends CollectionCommand{


    public RemoveByIdCommand(MusicBandCollection collection, CommandManager manager) {
        super(collection, manager);
    }

    @Override
    public void execute(List<String> args){
        collection.remove(Integer.parseInt(args.get(0)));
    }

    @Override
    public String getDescription() {
        return "Удаляет элемент по id";
    }

    @Override
    public String getName() {
        return "remove_by_id id";
    }
}
