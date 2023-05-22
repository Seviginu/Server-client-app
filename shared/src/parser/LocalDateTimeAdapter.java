package parser;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

/** Adapter of LocalDateTime for gson library */
public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {

  @Override
  public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
    jsonWriter.value(Objects.requireNonNullElseGet(localDateTime, LocalDateTime::now).toString());
  }

  @Override
  public LocalDateTime read(JsonReader jsonReader) throws IOException {
    return LocalDateTime.parse(jsonReader.nextString());
  }
}
