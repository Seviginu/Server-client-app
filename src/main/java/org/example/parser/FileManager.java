package org.example.parser;

import com.google.gson.Gson;
import org.example.collection.MusicBandCollection;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class FileManager {
    private File file;
    private final Gson gson = new Gson();

    public FileManager(File file) {
        this.file = file;
    }

    public boolean readable(){
        return file.canRead();
    }

    public boolean writable() {
        return file.canWrite();

    }

    public MusicBandCollection jsonToObj() throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        return gson.fromJson(inputStreamReader, MusicBandCollection.class);
    }

    public void objToJson(MusicBandCollection collection) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);

        String outputString = gson.toJson((Object) collection, collection.getClass());
        outputStream.write(outputString.getBytes());

    }
}
