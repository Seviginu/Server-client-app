package collection.element;

import collection.Printable;
import java.util.Objects;

public class Person extends Printable {
  private String name; // Поле не может быть null, Строка не может быть пустой
  private Float height; // Поле может быть null, Значение поля должно быть больше 0
  @EnumString
  private Color hairColor; // Поле не может быть null
  @EnumString
  private Country nationality; // Поле не может быть null
  @Entity
  @Name(name="location_id")
  private Location location; // Поле не может быть null

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Float getHeight() {
    return height;
  }

  public void setHeight(Float height) {
    this.height = height;
  }

  public Color getHairColor() {
    return hairColor;
  }

  public void setHairColor(Color hairColor) {
    this.hairColor = hairColor;
  }

  public Country getNationality() {
    return nationality;
  }

  public void setNationality(Country nationality) {
    this.nationality = nationality;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, height, hairColor, nationality, location);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) return false;
    if (o.getClass() != this.getClass()) return false;
    return name.equals(((Person) o).getName())
        && height.equals(((Person) o).getHeight())
        && hairColor.equals(((Person) o).getHairColor())
        && nationality.equals(((Person) o).getNationality())
        && location.equals(((Person) o).getLocation());
  }
}
