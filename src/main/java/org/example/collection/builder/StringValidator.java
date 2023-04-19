package org.example.collection.builder;


public interface StringValidator<T> extends Validator<T> {
  default boolean validateString(String value) {
    return validate(fromString(value));
  }

    T fromString(String value);
}
