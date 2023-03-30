package org.example.command;

import org.example.collection.MusicBandCollection;
import org.example.parser.FileManager;

import java.io.IOException;

public class SaveCommand extends FileCommand{

    public SaveCommand(MusicBandCollection collection, FileManager file) {
        super(collection, file);
    }

    @Override
    public void execute(String[] args) {
        try {
            file.objToJson(collection);
        }
        catch (IOException e){
            e.printStackTrace();
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
