package org.example.collection.builder;

import org.example.cli.ConsoleUserAsker;
import org.example.cli.UserInputChannel;
import org.example.cli.UserOutputChannel;
import org.example.collection.element.Color;
import org.example.collection.element.Coordinates;
import org.example.collection.element.Country;
import org.example.collection.element.Person;
import org.example.collection.exceptions.BuildException;

/** Build {@link Person} object with {@link UserInputChannel} */
public class PersonBuilder extends Builder<Person> {

  {
    element = new Person();
  }

  /**
   * Create a new instance with I/O channels and {@code userMode}
   *
   * @param inputChannel channel to get values from user
   * @param outputChannel channel to send promoting messages to user
   * @param userMode if false, suppress output messages
   */
  public PersonBuilder(
      UserInputChannel inputChannel, UserOutputChannel outputChannel, boolean userMode) {
    super(inputChannel, outputChannel, userMode);
  }

  private void setName() {
    if (userMode)
      outputChannel.sendStringLine(
          "Введите значение поля name." + " Значение не может быть пустым");
    String value = inputChannel.getString();
    StringValidator<String> validator = Validators.getPersonNameValidator();

    if (!validator.validateString(value)) {
      ConsoleUserAsker<String> consoleUserAsker = new ConsoleUserAsker<>();
      value = consoleUserAsker.askUser(validator, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setName(value);
        return;
      }
    }
    element.setName(value);
  }

  private void setHeight() {
    if (userMode)
      outputChannel.sendStringLine(
          "Введите значение поля height." + " Значение должно быть числом больше 0");
    String stringValue = inputChannel.getString();
    Float value;
    StringValidator<Float> validator = Validators.getPersonHeightValidator();
    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<Float> consoleUserAsker = new ConsoleUserAsker<>();
      value = consoleUserAsker.askUser(validator, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setHeight(value);
        return;
      }
    }
    value = validator.fromString(stringValue);
    element.setHeight(value);
  }

  private void setHairColor() {
    if (userMode) {
      outputChannel.sendStringLine("Введите число для выбора Color:");
      int counter = 0;
      for (Color color : Color.values()) outputChannel.sendStringLine(counter++ + ") " + color);
    }

    StringValidator<Color> validator = Validators.getPersonHairColorValidator();

    String stringValue = inputChannel.getString();

    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<Color> consoleUserAsker = new ConsoleUserAsker<>();
      Color value = consoleUserAsker.askUser(validator, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setHairColor(value);
        return;
      }
    }
    element.setHairColor(validator.fromString(stringValue));
  }

  private void setNationality() {
    if (userMode) {
      outputChannel.sendStringLine("Введите число для выбора country:");
      int counter = 0;
      for (Country country : Country.values())
        outputChannel.sendStringLine(counter++ + ") " + country);
    }

    StringValidator<Country> validator = Validators.getPersonNationalityValidator();

    String stringValue = inputChannel.getString();

    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<Country> consoleUserAsker = new ConsoleUserAsker<>();
      Country value = consoleUserAsker.askUser(validator, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setNationality(value);
        return;
      }
    }
    element.setNationality(validator.fromString(stringValue));
  }

  private void setLocation() {
    element.setLocation(new LocationBuilder(inputChannel, outputChannel, userMode).getElement());
  }

  /**
   * Build {@link Coordinates} object
   *
   * @return created coordinates
   */
  @Override
  public Person getElement() {
    setName();
    setHeight();
    setHairColor();
    setNationality();
    setLocation();
    return element;
  }
}
