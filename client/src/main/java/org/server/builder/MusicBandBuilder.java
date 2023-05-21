package org.server.builder;

import IO.UserInputChannel;
import IO.UserOutputChannel;
import collection.MusicBandCollection;
import collection.builder.StringValidator;
import collection.builder.Validators;
import collection.element.MusicBand;
import collection.element.MusicGenre;
import collection.exceptions.BuildException;
import java.time.LocalDateTime;
import org.server.userIO.ConsoleUserAsker;

/** Build {@link MusicBand} object with {@link UserInputChannel} */
public class MusicBandBuilder extends Builder<MusicBand> {
  {
    element = new MusicBand();
  }

  private final boolean newElement;
  /**
   * Create a new instance with I/O channels and {@code userMode}
   *
   * @param inputChannel channel to get values from user
   * @param outputChannel channel to send promoting messages to user
   * @param userMode if false, suppress output messages
   */
  public MusicBandBuilder(
      UserInputChannel inputChannel, UserOutputChannel outputChannel, boolean userMode) {
    super(inputChannel, outputChannel, userMode);
    newElement = true;
  }

  private void setName() {
    if (userMode)
      outputChannel.sendStringLine(
          "Введите значение поля name." + " Значение не может быть пустым");
    String value = inputChannel.getString();
    StringValidator<String> validator = Validators.getMusicBandNameValidator();

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

  private void setNumberOfParticipants() {
    if (userMode) outputChannel.sendStringLine("Введите значение поля numberOfParticipants");
    String stringValue = inputChannel.getString();
    Integer value;
    StringValidator<Integer> validator = Validators.getMusicBandParticipantsValidator();

    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<Integer> consoleUserAsker = new ConsoleUserAsker<>();
      value = consoleUserAsker.askUser(validator, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setNumberOfParticipants(value);
        return;
      }
    }
    value = validator.fromString(stringValue);
    element.setNumberOfParticipants(value);
  }

  private void setAlbumsCount() {
    if (userMode)
      outputChannel.sendStringLine(
          "Введите значение поля albumsCount." + " Значение должно быть положительным");
    String stringValue = inputChannel.getString();
    Integer value;
    StringValidator<Integer> validator = Validators.getMusicBandAlbumsCountValidator();

    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<Integer> consoleUserAsker = new ConsoleUserAsker<>();
      value = consoleUserAsker.askUser(validator, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setAlbumsCount(value);
        return;
      }
    }
    value = validator.fromString(stringValue);
    element.setAlbumsCount(value);
  }

  private void setDescription() {
    if (userMode)
      outputChannel.sendStringLine(
          "Введите значение поля descripion." + " Значение не может быть пустым");
    String value = inputChannel.getString();
    StringValidator<String> validator = Validators.getMusicBandDescriptionValidator();

    if (!validator.validateString(value)) {
      ConsoleUserAsker<String> consoleUserAsker = new ConsoleUserAsker<>();
      value = consoleUserAsker.askUser(validator, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setDescription(value);
        return;
      }
    }
    element.setDescription(value);
  }

  private void setGenre() {
    if (userMode) {
      outputChannel.sendStringLine("Введите число для выбора genre:");
      int counter = 0;
      for (MusicGenre genre : MusicGenre.values())
        outputChannel.sendStringLine(counter++ + ") " + genre);
    }

    StringValidator<MusicGenre> validator = Validators.getMusicBandGenreValidator();

    String stringValue = inputChannel.getString();

    if (!validator.validateString(stringValue)) {
      ConsoleUserAsker<MusicGenre> consoleUserAsker = new ConsoleUserAsker<>();
      MusicGenre value = consoleUserAsker.askUser(validator, 3);
      if (!validator.validate(value)) throw new BuildException();
      else {
        element.setGenre(value);
        return;
      }
    }
    element.setGenre(validator.fromString(stringValue));
  }

  private void setCoordinates() {
    element.setCoordinates(
        new CoordinatesBuilder(inputChannel, outputChannel, userMode).getElement());
  }

  private void setFrontMan() {
    element.setFrontMan(new PersonBuilder(inputChannel, outputChannel, userMode).getElement());
  }

  private void setId() {
    element.setId(MusicBandCollection.generateId());
  }

  private void setCreationDate() {
    LocalDateTime date = LocalDateTime.now();
    element.setCreationDate(date);
  }

  /**
   * Build {@link MusicBand} object
   *
   * @return created coordinates
   */
  public MusicBand getElement() {
    setName();
    setCoordinates();
    setNumberOfParticipants();
    setAlbumsCount();
    setDescription();
    setGenre();
    setFrontMan();
    if (newElement) {
      setCreationDate();
      setId();
    }
    return element;
  }
}
