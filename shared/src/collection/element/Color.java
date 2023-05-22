package collection.element;

import java.io.Serializable;

public enum Color implements Serializable {
  RED("Красный"),
  BLACK("Черный"),
  YELLOW("Желтый");

  private final String name;

  Color(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
