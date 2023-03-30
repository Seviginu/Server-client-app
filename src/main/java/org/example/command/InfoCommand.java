package org.example.command;

import org.example.collection.MusicBandCollection;

public class InfoCommand extends CollectionCommand {
    public InfoCommand(MusicBandCollection collection) {
        super(collection);
    }

    @Override
    public void execute(String[] args) {
        String string = "Время создания: " +
                collection.getCreationTime() +
                "\nВремя обновления: " +
                collection.getUpdateTime() +
                "Количество элементов в коллекции: " +
                collection.getListOfElements().size();
        System.out.println(string);
    }

    @Override
    public String getDescription() {
        return "Выводит информацию о коллекции";
    }

    @Override
    public String getName() {
        return "info";
    }
}
