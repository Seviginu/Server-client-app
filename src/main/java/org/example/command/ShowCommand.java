package org.example.command;

import org.example.collection.MusicBandCollection;
import org.example.collection.element.MusicBand;

public class ShowCommand extends CollectionCommand{
    public ShowCommand(MusicBandCollection collection) {
        super(collection);
    }

    @Override
    public void execute() {
        for (MusicBand band : collection.getListOfElements()){
            System.out.println(band + "\n");
        }
    }

    @Override
    public String getDescription() {
        return "Выводит строковое представление всех элементов коллекции";
    }

    @Override
    public String getName() {
        return "show";
    }
}
