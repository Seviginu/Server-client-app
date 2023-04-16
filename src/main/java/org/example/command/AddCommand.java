package org.example.command;

import org.example.collection.MusicBandCollection;
import org.example.collection.builder.MusicBandBuilder;
import org.example.collection.element.MusicBand;

import java.util.List;

public class AddCommand extends CollectionCommand{
    public AddCommand(MusicBandCollection collection, CommandManager manager) {
        super(collection, manager);
    }

    @Override
    public void execute(List<String> args) {
        MusicBandBuilder builder = new MusicBandBuilder(manager.getChannel());
        collection.add(builder.getElement());
    }

    @Override
    public String getDescription() {
        return "Добавляет элемент в коллекцию";
    }

    @Override
    public String getName() {
        return "add";
    }
}
