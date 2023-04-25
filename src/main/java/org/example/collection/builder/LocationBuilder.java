package org.example.collection.builder;

import org.example.cli.ConsoleUserAsker;
import org.example.cli.UserChannel;
import org.example.cli.UserInputChannel;
import org.example.cli.UserOutputChannel;
import org.example.collection.element.Location;
import org.example.collection.exceptions.BuildException;

public class LocationBuilder extends Builder<Location> {

  {
    element = new Location();
  }

  public LocationBuilder(UserInputChannel inputChannel, Location element) {
    super(inputChannel, element);
  }

  public LocationBuilder(UserChannel channel, Location element) {
    super(channel, element);
  }

  public LocationBuilder(
      UserInputChannel inputChannel, UserOutputChannel outputChannel, boolean userMode) {
    super(inputChannel, outputChannel, userMode);
  }

  public void setX() {
    if (userMode)
      outputChannel.sendStringLine(
          "Введите значение поля X." + " Значение не должно превышать 258");
    String stringValue = inputChannel.getString();
    Long value;
    StringValidator<Long> validator = Validators.getLocationXValidator();
    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<Long> consoleUserAsker = new ConsoleUserAsker<>();
      value = consoleUserAsker.askUser(validator, inputChannel, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setX(value);
        return;
      }
    }
    value = validator.fromString(stringValue);
    element.setX(value);
  }

  public void setY() {
    if (userMode)
      outputChannel.sendStringLine("Введите значение поля Y." + " Значение должно быть числом");
    String stringValue = inputChannel.getString();
    Float value;
    StringValidator<Float> validator = Validators.getLocationYValidator();
    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<Float> consoleUserAsker = new ConsoleUserAsker<>();
      value = consoleUserAsker.askUser(validator, inputChannel, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setY(value);
        return;
      }
    }
    value = validator.fromString(stringValue);
    element.setY(value);
  }

  public void setZ() {
    if (userMode)
      outputChannel.sendStringLine(
          "Введите значение поля Z." + " Значение не должно превышать 258");
    String stringValue = inputChannel.getString();
    Long value;
    StringValidator<Long> validator = Validators.getLocationZValidator();
    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<Long> consoleUserAsker = new ConsoleUserAsker<>();
      value = consoleUserAsker.askUser(validator, inputChannel, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setZ(value);
        return;
      }
    }
    value = validator.fromString(stringValue);
    element.setZ(value);
  }

  public void setName() {
    if (userMode)
      outputChannel.sendStringLine(
          "Введите значение поля name." + " Значение не может быть пустым");
    String value = inputChannel.getString();
    StringValidator<String> validator = Validators.getLocationNameValidator();

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

  @Override
  Location getElement() {
    setX();
    setY();
    setZ();
    setName();
    return element;
  }
}
