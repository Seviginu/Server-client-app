package collection;

import java.lang.reflect.Field;

/**
 * Class that overrides {@link #toString()} to display the object nicely. Iterates by all class
 * fields and display it like: [field_name]: [field_value]. If field also instance of {@code
 * Printable} highlight field_name with green color and makes indent before every field of this
 * object. (indent = "--")
 */
public abstract class Printable {
  private static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_RESET = "\u001B[0m";

  /**
   * Display object with indent. Each line starts with {@code "--"*indent}
   *
   * @param indent size of indent
   * @return string display of object
   */
  protected String toString(int indent) {
    StringBuilder stringBuilder = new StringBuilder();
    for (Field field : this.getClass().getDeclaredFields()) {
      try {
        field.setAccessible(true);
        Object value = field.get(this);
        if (value instanceof Printable) {
          stringBuilder.append(ANSI_GREEN);
        }
        stringBuilder.append("--".repeat(Math.max(0, indent)));
        stringBuilder.append(field.getName()).append(": ");
        stringBuilder.append(ANSI_RESET);
        if (value instanceof Printable) {
          stringBuilder.append("\n");
          stringBuilder.append(((Printable) value).toString(indent + 1));
        } else stringBuilder.append(value);
        stringBuilder.append("\n");
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString().strip();
  }

  /**
   * Return {@link #toString(int)} with indent = 0)}
   *
   * @return string display of object
   */
  @Override
  public String toString() {
    return toString(0);
  }
}
