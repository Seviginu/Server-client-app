package collection.element;

public enum Color {
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
