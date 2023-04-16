package org.example.collection.builder;

import com.sun.source.tree.ForLoopTree;
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
    StringValidator<Long> validator =
        new StringValidator<>() {
          @Override
          public boolean validate(Long value) {
            return value != null && value < 258;
          }

          @Override
          public Long fromString(String value) {
            try {
              return Long.parseLong(value);
            } catch (Exception e) {
              return null;
            }
          }

          @Override
          public String getRequirements() {
            return "Значние X не должно превышать 258";
          }
        };
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
    StringValidator<Float> validator =
        new StringValidator<>() {
          @Override
          public boolean validate(Float value) {
            return value != null;
          }

          @Override
          public Float fromString(String value) {
            try {
              return Float.parseFloat(value);
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
    StringValidator<Long> validator =
        new StringValidator<>() {
          @Override
          public boolean validate(Long value) {
            return value != null;
          }

          @Override
          public Long fromString(String value) {
            try {
              return Long.parseLong(value);
            } catch (Exception e) {
              return null;
            }
          }

          @Override
          public String getRequirements() {
            return "Значние Z должна быть числом";
          }
        };
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
    StringValidator<String> validator =
        new StringValidator<>() {
          @Override
          public boolean validateString(String value) {
            return validate(value);
          }

          @Override
          public String fromString(String value) {
            return value;
          }

          @Override
          public boolean validate(String value) {
            return value != null && !value.isEmpty() && value.length() <= 396;
          }

          @Override
          public String getRequirements() {
            return "Значние name не может быть пустым и не может содержать более 396 символов";
          }
        };

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
