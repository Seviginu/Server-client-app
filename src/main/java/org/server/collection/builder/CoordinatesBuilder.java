package org.server.collection.builder;

import org.server.cli.ConsoleUserAsker;
import org.server.cli.UserInputChannel;
import org.server.cli.UserOutputChannel;
import org.server.collection.element.Coordinates;
import org.server.collection.exceptions.BuildException;

/** Build {@link Coordinates} object with {@link UserInputChannel} */
public class CoordinatesBuilder extends Builder<Coordinates> {

  {
    element = new Coordinates();
  }

  /**
   * Create a new instance with I/O channels and {@code userMode}
   *
   * @param inputChannel channel to get values from user
   * @param outputChannel channel to send promoting messages to user
   * @param userMode if false, suppress output messages
   */
  public CoordinatesBuilder(
      UserInputChannel inputChannel, UserOutputChannel outputChannel, boolean userMode) {
    super(inputChannel, outputChannel, userMode);
  }

  private void setX() {
    if (userMode)
      outputChannel.sendStringLine("Введите значение поля X. Значение должно быть числом");
    String stringValue = inputChannel.getString();
    Double value;
    StringValidator<Double> validator = Validators.getCoordinatesXValidator();
    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<Double> consoleUserAsker = new ConsoleUserAsker<>();
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
    Double value;
    StringValidator<Double> validator = Validators.getCoordinatesYValidator();
    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<Double> consoleUserAsker = new ConsoleUserAsker<>();
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

  /**
   * Build {@link Coordinates} object
   *
   * @return created coordinates
   */
  @Override
  Coordinates getElement() {
    setX();
    setY();
    return element;
  }
}
