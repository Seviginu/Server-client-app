package org.example.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.collection.MusicBandCollection;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

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
    FileInputStream inputStream = new FileInputStream(file);
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

    return gson.fromJson(inputStreamReader, MusicBandCollection.class);
  }

  public void objToJson(MusicBandCollection collection) throws IOException {
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);

    String outputString = gson.toJson(collection, collection.getClass());
    outputStream.write(outputString.getBytes());
    outputStream.close();
    fileOutputStream.close();
  }
}
