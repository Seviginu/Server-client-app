package org.example.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.time.LocalDateTime;
import org.example.collection.MusicBandCollection;

/** Class to load/save instance of {@link MusicBandCollection} to/from json file */
public class FileManager {
  private final File file;
  private final Gson gson;

  /**
   * Creates instance with json file
   *
   * @param file json file with collection
   */
  public FileManager(File file) {
    this.file = file;
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
    gson = builder.create();
  }

  public boolean isExist() {
    return file.exists() && file.isFile();
  }

  public boolean readable() {
    return file.canRead();
  }

  public boolean writable() {
    return file.canWrite();
  }

  /**
   * Load collection from json file
   *
   * @return collection of elements
   * @throws IOException when can't read file
   */
  public MusicBandCollection jsonToObj() throws IOException {
    try (FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {

      return gson.fromJson(inputStreamReader, MusicBandCollection.class);
    }
  }

  /**
   * Save collection to json file
   *
   * @param collection {@link MusicBandCollection} to save
   * @throws IOException when can't write file
   */
  public void objToJson(MusicBandCollection collection) throws IOException {
    try (FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream)) {

      String outputString = gson.toJson(collection, collection.getClass());
      outputStream.write(outputString.getBytes());
    }
  }
}
