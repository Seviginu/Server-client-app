package org.example.collection;

import java.lang.reflect.Field;

public abstract class Printable {
  private static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_RESET = "\u001B[0m";

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (Field field : this.getClass().getDeclaredFields()) {
      try {
        field.setAccessible(true);
        Object value = field.get(this);
        if (value instanceof Printable) {
          stringBuilder.append(ANSI_GREEN);
        }
        stringBuilder.append(field.getName()).append(": ");
        stringBuilder.append(ANSI_RESET);
        if (value instanceof Printable) stringBuilder.append("\n");
        stringBuilder.append(value).append("\n");
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString().strip();
  }
}
