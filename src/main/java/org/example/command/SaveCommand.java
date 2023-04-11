package org.example.command;

import org.example.collection.MusicBandCollection;
import org.example.parser.FileManager;

import java.io.IOException;

public class SaveCommand extends FileCommand{

    public SaveCommand(MusicBandCollection collection, FileManager file, CommandManager manager) {
        super(collection, file, manager);
    }

    @Override
    public void execute(String[] args) {
        try {
            file.objToJson(collection);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Сохраняет коллекцию в файл";
    }

    @Override
    public String getName() {
        return "save";
    }
}
