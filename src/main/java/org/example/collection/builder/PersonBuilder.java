package org.example.collection.builder;

import org.example.cli.ConsoleUserAsker;
import org.example.cli.UserChannel;
import org.example.cli.UserInputChannel;
import org.example.cli.UserOutputChannel;
import org.example.collection.element.Color;
import org.example.collection.element.Country;
import org.example.collection.element.MusicGenre;
import org.example.collection.element.Person;
import org.example.collection.exceptions.BuildException;

import java.util.Objects;
import java.util.function.Function;

public class PersonBuilder extends Builder<Person> {

  {
    element = new Person();
  }

  public PersonBuilder(UserInputChannel inputChannel, Person element) {
    super(inputChannel, element);
  }

  public PersonBuilder(UserChannel channel, Person element) {
    super(channel, element);
  }

  public PersonBuilder(
      UserInputChannel inputChannel, UserOutputChannel outputChannel, boolean userMode) {
    super(inputChannel, outputChannel, userMode);
  }

  public void setName() {
    if (userMode)
      outputChannel.sendStringLine(
          "Введите значение поля name." + " Значение не может быть пустым");
    String value = inputChannel.getString();
    StringValidator<String> validator = Validators.getPersonNameValidator();

    if (!validator.validateString(value)) {
      ConsoleUserAsker<String> consoleUserAsker = new ConsoleUserAsker<>();
      value = consoleUserAsker.askUser(validator, inputChannel, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setName(value);
        return;
      }
    }
    element.setName(value);
  }

  public void setHeight() {
    if (userMode)
      outputChannel.sendStringLine(
          "Введите значение поля height." + " Значение должно быть числом больше 0");
    String stringValue = inputChannel.getString();
    Float value;
    StringValidator<Float> validator = Validators.getPersonHeightValidator();
    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<Float> consoleUserAsker = new ConsoleUserAsker<>();
      value = consoleUserAsker.askUser(validator, inputChannel, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setHeight(value);
        return;
      }
    }
    value = validator.fromString(stringValue);
    element.setHeight(value);
  }

  public void setHairColor() {
    if (userMode) {
      outputChannel.sendStringLine("Введите число для выбора Color:");
      int counter = 0;
      for (Color color : Color.values()) outputChannel.sendStringLine(counter++ + ") " + color);
    }

    StringValidator<Color> validator = Validators.getPersonHairColorValidator();

    String stringValue = inputChannel.getString();

    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<Color> consoleUserAsker = new ConsoleUserAsker<>();
      Color value = consoleUserAsker.askUser(validator, inputChannel, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setHairColor(value);
        return;
      }
    }
    element.setHairColor(validator.fromString(stringValue));
  }

  public void setNationality() {
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
      Country value = consoleUserAsker.askUser(validator, inputChannel, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setNationality(value);
        return;
      }
    }
    element.setNationality(validator.fromString(stringValue));
  }

  public void setLocation() {
    element.setLocation(new LocationBuilder(inputChannel, outputChannel, userMode).getElement());
  }

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
