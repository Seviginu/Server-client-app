package collection.builder;

import collection.element.*;
import parser.FileManager;

import java.io.File;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/** Class that generate validators for different types */
public class Validators {

  private static class ValidatorGenerator<T> {
    public Validator<T> getStringValidator(
        Predicate<T> predicate, Function<String, T> fromString, String requirements) {
      return new StringValidator<>() {

        @Override
        public T fromString(String value) {
          return fromString.apply(value);
        }

        @Override
        public boolean validate(T value) {
          return predicate.test(value);
        }

        @Override
        public String getRequirements() {
          return requirements;
        }
      };
    }
  }

  public static StringValidator<String> getMusicBandNameValidator() {
    ValidatorGenerator<String> generator = new ValidatorGenerator<>();
    return (StringValidator<String>)
        generator.getStringValidator(
            o -> Objects.nonNull(o) && !o.isEmpty(), o -> o, "Поле name не может быть пустым");
  }

  public static StringValidator<Integer> getMusicBandParticipantsValidator() {
    ValidatorGenerator<Integer> generator = new ValidatorGenerator<>();
    return (StringValidator<Integer>)
        generator.getStringValidator(
            o -> Objects.nonNull(o) && o > 0,
            o -> {
              try {
                return Integer.parseInt(o);
              } catch (NumberFormatException e) {
                return null;
              }
            },
            "Поле numberOfParticipants должно быть числом");
  }

  public static StringValidator<Integer> getMusicBandAlbumsCountValidator() {
    ValidatorGenerator<Integer> generator = new ValidatorGenerator<>();
    return (StringValidator<Integer>)
        generator.getStringValidator(
            o -> Objects.nonNull(o) && o > 0,
            o -> {
              try {
                return Integer.parseInt(o);
              } catch (NumberFormatException e) {
                return null;
              }
            },
            "Поле albumsCount должно быть больше нуля");
  }

  public static StringValidator<String> getMusicBandDescriptionValidator() {
    ValidatorGenerator<String> generator = new ValidatorGenerator<>();
    return (StringValidator<String>)
        generator.getStringValidator(
            o -> Objects.nonNull(o) && !o.isEmpty(),
            o -> o,
            "Поле description не может быть пустым");
  }

  public static StringValidator<MusicGenre> getMusicBandGenreValidator() {
    ValidatorGenerator<MusicGenre> generator = new ValidatorGenerator<>();
    return (StringValidator<MusicGenre>)
        generator.getStringValidator(
            Objects::nonNull,
            s -> {
              try {
                int num = Integer.parseInt(s);
                return MusicGenre.values()[num];
              } catch (Exception e) {
                return null;
              }
            },
            "Значение должно быть в диапазоне 0-" + (MusicGenre.values().length-1));
  }

  public static StringValidator<Double> getCoordinatesXValidator() {
    ValidatorGenerator<Double> generator = new ValidatorGenerator<>();
    return (StringValidator<Double>)
        generator.getStringValidator(
            Objects::nonNull,
            o -> {
              try {
                return Double.parseDouble(o);
              } catch (NumberFormatException e) {
                return null;
              }
            },
            "Поле coordinates.x должно быть числом");
  }

  public static StringValidator<Double> getCoordinatesYValidator() {
    ValidatorGenerator<Double> generator = new ValidatorGenerator<>();
    return (StringValidator<Double>)
        generator.getStringValidator(
            Objects::nonNull,
            o -> {
              try {
                return Double.parseDouble(o);
              } catch (NumberFormatException e) {
                return null;
              }
            },
            "Поле coordinates.y должно быть числом");
  }

  public static StringValidator<String> getPersonNameValidator() {
    ValidatorGenerator<String> generator = new ValidatorGenerator<>();
    return (StringValidator<String>)
        generator.getStringValidator(
            o -> Objects.nonNull(o) && !o.isEmpty(),
            o -> o,
            "Поле person.name не может быть пустым");
  }

  public static StringValidator<Float> getPersonHeightValidator() {
    ValidatorGenerator<Float> generator = new ValidatorGenerator<>();
    return (StringValidator<Float>)
        generator.getStringValidator(
            o -> Objects.nonNull(o) && o > 0,
            o -> {
              try {
                return Float.parseFloat(o);
              } catch (NumberFormatException e) {
                return null;
              }
            },
            "Поле person.height должно быть больше 0");
  }

  public static StringValidator<Color> getPersonHairColorValidator() {
    ValidatorGenerator<Color> generator = new ValidatorGenerator<>();
    return (StringValidator<Color>)
        generator.getStringValidator(
            Objects::nonNull,
            s -> {
              try {
                int num = Integer.parseInt(s);
                return Color.values()[num];
              } catch (Exception e) {
                return null;
              }
            },
            "Значение должно быть в диапазоне 0-" + (Color.values().length-1));
  }

  public static StringValidator<Country> getPersonNationalityValidator() {
    ValidatorGenerator<Country> generator = new ValidatorGenerator<>();
    return (StringValidator<Country>)
        generator.getStringValidator(
            Objects::nonNull,
            s -> {
              try {
                int num = Integer.parseInt(s);
                return Country.values()[num];
              } catch (Exception e) {
                return null;
              }
            },
            "Значение должно быть в диапазоне 0-" + (Country.values().length-1));
  }

  public static StringValidator<Long> getLocationXValidator() {
    ValidatorGenerator<Long> generator = new ValidatorGenerator<>();
    return (StringValidator<Long>)
        generator.getStringValidator(
            o -> Objects.nonNull(o) && o < 258,
            o -> {
              try {
                return Long.parseLong(o);
              } catch (NumberFormatException e) {
                return null;
              }
            },
            "Поле location.x должно быть числом меньше 258");
  }

  public static StringValidator<Float> getLocationYValidator() {
    ValidatorGenerator<Float> generator = new ValidatorGenerator<>();
    return (StringValidator<Float>)
        generator.getStringValidator(
            Objects::nonNull,
            o -> {
              try {
                return Float.parseFloat(o);
              } catch (NumberFormatException e) {
                return null;
              }
            },
            "Поле location.y должно быть числом");
  }

  public static StringValidator<Long> getLocationZValidator() {
    ValidatorGenerator<Long> generator = new ValidatorGenerator<>();
    return (StringValidator<Long>)
        generator.getStringValidator(
            Objects::nonNull,
            o -> {
              try {
                return Long.parseLong(o);
              } catch (NumberFormatException e) {
                return null;
              }
            },
            "Поле location.z должно быть числом");
  }

  public static StringValidator<String> getLocationNameValidator() {
    ValidatorGenerator<String> generator = new ValidatorGenerator<>();
    return (StringValidator<String>)
        generator.getStringValidator(
            o -> Objects.nonNull(o) && !o.isEmpty(),
            o -> o,
            "Поле location.name не может быть пустым");
  }

  public static StringValidator<FileManager> getFileSaverValidator() {
    ValidatorGenerator<FileManager> generator = new ValidatorGenerator<>();
    return (StringValidator<FileManager>)
        generator.getStringValidator(
            o -> o.isExist() && o.writable(),
            o -> new FileManager(new File(o)),
            "Файл не существует или закрыт для записи");
  }

  public static Validator<Location> getLocationValidator(){
      return new Validator<Location>() {
          @Override
          public boolean validate(Location value) {
              return value != null &&
                      getLocationXValidator().validate(value.getX()) &&
                      getLocationYValidator().validate(value.getY()) &&
                      getLocationZValidator().validate(value.getZ()) &&
                      getLocationNameValidator().validate(value.getName());
          }

          @Override
          public String getRequirements() {
              return null;
          }
      };
  }

  public static Validator<Person> getPersonValidator(){
      return new Validator<>() {
          @Override
          public boolean validate(Person value) {
              return value != null &&
                      getPersonNameValidator().validate(value.getName()) &&
                      getPersonHeightValidator().validate(value.getHeight()) &&
                      getPersonHairColorValidator().validate(value.getHairColor()) &&
                      getPersonNationalityValidator().validate(value.getNationality()) &&
                      getLocationValidator().validate(value.getLocation());
          }

          @Override
          public String getRequirements() {
              return null;
          }
      };
  }

  public static Validator<Coordinates> getCoordinatesValidator(){
      return new Validator<>() {
          @Override
          public boolean validate(Coordinates value) {
              return value != null &&
                      getCoordinatesXValidator().validate(value.getX()) &&
                      getCoordinatesYValidator().validate(value.getY());
          }

          @Override
          public String getRequirements() {
              return null;
          }
      };
  }

  public static Validator<MusicBand> getMusicBandValidator(){
      return new Validator<>() {
          @Override
          public boolean validate(MusicBand value) {
              return value != null &&
                      getMusicBandNameValidator().validate(value.getName()) &&
                      getMusicBandParticipantsValidator().validate(value.getNumberOfParticipants()) &&
                      getMusicBandDescriptionValidator().validate(value.getDescription()) &&
                      getMusicBandGenreValidator().validate(value.getGenre()) &&
                      getMusicBandAlbumsCountValidator().validate(value.getAlbumsCount()) &&
                      getCoordinatesValidator().validate(value.getCoordinates()) &&
                      getPersonValidator().validate(value.getFrontMan());
          }

          @Override
          public String getRequirements() {
              return null;
          }
      };
  }
}
