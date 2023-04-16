package org.example.collection.builder;

import org.example.cli.ConsoleUserAsker;
import org.example.cli.UserChannel;
import org.example.cli.UserInputChannel;
import org.example.cli.UserOutputChannel;
import org.example.collection.element.Coordinates;
import org.example.collection.exceptions.BuildException;

public class CoordinatesBuilder extends Builder<Coordinates> {

  {
    element = new Coordinates();
  }

  public CoordinatesBuilder(UserInputChannel inputChannel, Coordinates element) {
    super(inputChannel, element);
  }

  public CoordinatesBuilder(UserChannel channel, Coordinates element) {
    super(channel, element);
  }

  public CoordinatesBuilder(
      UserInputChannel inputChannel, UserOutputChannel outputChannel, boolean userMode) {
    super(inputChannel, outputChannel, userMode);
  }

  public void setX() {
    if (userMode)
      outputChannel.sendStringLine("Введите значение поля X. Значение должно быть числом");
    String stringValue = inputChannel.getString();
    Double value;
    StringValidator<Double> validator =
        new StringValidator<>() {
          @Override
          public boolean validate(Double value) {
            return value != null;
          }

          @Override
          public Double fromString(String value) {
            try {
              return Double.parseDouble(value);
            } catch (Exception e) {
              return null;
            }
          }

          @Override
          public String getRequirements() {
            return "Значние X должно быть числом";
          }
        };
    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<Double> consoleUserAsker = new ConsoleUserAsker<>();
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
    Double value;
    StringValidator<Double> validator =
        new StringValidator<>() {
          @Override
          public boolean validate(Double value) {
            return value != null;
          }

          @Override
          public Double fromString(String value) {
            try {
              return Double.parseDouble(value);
            } catch (Exception e) {
              return null;
            }
          }

          @Override
          public String getRequirements() {
            return "Значние Y должно быть числом";
          }
        };
    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<Double> consoleUserAsker = new ConsoleUserAsker<>();
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

  @Override
  Coordinates getElement() {
    setX();
    setY();
    return element;
  }
}
