package org.server.command.commands;

import collection.MusicBandCollection;
import org.server.command.CommandManager;

import java.util.List;

public class GetCollectionCommand extends CollectionCommand{
    public GetCollectionCommand(MusicBandCollection collection, CommandManager manager) {
        super(collection, manager);
    }

    @Override
    public void execute(List<String> args) {

    }

    @Override
    public String getDescription() {
        return "возвращает на клиент экземпляр MusicBandCollection";
    }

    @Override
    public String getName() {
        return "get_collection";
    }
}
