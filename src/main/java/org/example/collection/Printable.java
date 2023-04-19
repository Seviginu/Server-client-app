package org.example.collection;

import java.lang.reflect.Field;

public abstract class Printable {
  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (Field field : this.getClass().getDeclaredFields()) {
      try {
        field.setAccessible(true);
        stringBuilder.append(field.getName()).append(": ").append(field.get(this)).append("\n");
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
  }
}
