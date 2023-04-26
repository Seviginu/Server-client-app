package org.example.collection.builder;

import org.example.cli.ConsoleUserAsker;
import org.example.cli.UserInputChannel;
import org.example.cli.UserOutputChannel;
import org.example.collection.element.Location;
import org.example.collection.exceptions.BuildException;

/** Build {@link Location} object with {@link UserInputChannel} */
public class LocationBuilder extends Builder<Location> {

  {
    element = new Location();
  }
  /**
   * Create a new instance with I/O channels and {@code userMode}
   *
   * @param inputChannel channel to get values from user
   * @param outputChannel channel to send promoting messages to user
   * @param userMode if false, suppress output messages
   */
  public LocationBuilder(
      UserInputChannel inputChannel, UserOutputChannel outputChannel, boolean userMode) {
    super(inputChannel, outputChannel, userMode);
  }

  private void setX() {
    if (userMode)
      outputChannel.sendStringLine(
          "Введите значение поля X." + " Значение не должно превышать 258");
    String stringValue = inputChannel.getString();
    Long value;
    StringValidator<Long> validator = Validators.getLocationXValidator();
    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<Long> consoleUserAsker = new ConsoleUserAsker<>();
      value = consoleUserAsker.askUser(validator, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setX(value);
        return;
      }
    }
    value = validator.fromString(stringValue);
    element.setX(value);
  }

  private void setY() {
    if (userMode)
      outputChannel.sendStringLine("Введите значение поля Y." + " Значение должно быть числом");
    String stringValue = inputChannel.getString();
    Float value;
    StringValidator<Float> validator = Validators.getLocationYValidator();
    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<Float> consoleUserAsker = new ConsoleUserAsker<>();
      value = consoleUserAsker.askUser(validator, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setY(value);
        return;
      }
    }
    value = validator.fromString(stringValue);
    element.setY(value);
  }

  private void setZ() {
    if (userMode)
      outputChannel.sendStringLine(
          "Введите значение поля Z." + " Значение не должно превышать 258");
    String stringValue = inputChannel.getString();
    Long value;
    StringValidator<Long> validator = Validators.getLocationZValidator();
    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<Long> consoleUserAsker = new ConsoleUserAsker<>();
      value = consoleUserAsker.askUser(validator, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setZ(value);
        return;
      }
    }
    value = validator.fromString(stringValue);
    element.setZ(value);
  }

  private void setName() {
    if (userMode)
      outputChannel.sendStringLine(
          "Введите значение поля name." + " Значение не может быть пустым");
    String value = inputChannel.getString();
    StringValidator<String> validator = Validators.getLocationNameValidator();

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

  /**
   * Build {@link Location} object
   *
   * @return created coordinates
   */
  @Override
  Location getElement() {
    setX();
    setY();
    setZ();
    setName();
    return element;
  }
}
