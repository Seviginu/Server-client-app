package org.example.collection;

import java.lang.reflect.Field;

public abstract class Printable {
  private static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_RESET = "\u001B[0m";

  protected String toString(int offset){
    StringBuilder stringBuilder = new StringBuilder();
    for (Field field : this.getClass().getDeclaredFields()) {
      try {
        field.setAccessible(true);
        Object value = field.get(this);
        if (value instanceof Printable) {
          stringBuilder.append(ANSI_GREEN);
        }
        stringBuilder.append("--".repeat(Math.max(0, offset)));
        stringBuilder.append(field.getName()).append(": ");
        stringBuilder.append(ANSI_RESET);
        if (value instanceof Printable) {
          stringBuilder.append("\n");
          stringBuilder.append(((Printable) value).toString(offset+1));
        }
        else stringBuilder.append(value);
        stringBuilder.append("\n");
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString().strip();
  }


  @Override
  public String toString() {
    return toString(0);
  }
}
