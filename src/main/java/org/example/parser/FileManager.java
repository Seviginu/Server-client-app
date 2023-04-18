package org.example.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.time.LocalDateTime;
import org.example.collection.MusicBandCollection;

public class FileManager {
  private File file;
  private final Gson gson;

  public FileManager(File file) {
    this.file = file;
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
    gson = builder.create();
  }

  public boolean readable() {
    return file.canRead();
  }

  public boolean writable() {
    return file.canWrite();
  }

  public MusicBandCollection jsonToObj() throws IOException {
    try (FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {

      return gson.fromJson(inputStreamReader, MusicBandCollection.class);
    }
  }

  public void objToJson(MusicBandCollection collection) throws IOException {
    try (FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream)) {

      String outputString = gson.toJson(collection, collection.getClass());
      outputStream.write(outputString.getBytes());
    }
  }
}
