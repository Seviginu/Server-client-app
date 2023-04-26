package org.example.collection.builder;

/**
 * Parse value to {@link String} and validate
 *
 * @param <T> type of validating value
 */
public interface StringValidator<T> extends Validator<T> {
  /**
   * Validate value by string representation of it.
   *
   * @param value string to validate
   * @return {@code true} if string is correct, otherwise {@code false}
   */
  default boolean validateString(String value) {
    return validate(fromString(value));
  }

  /**
   * Get object of type {@code T} by string representation
   *
   * @param value string representation of value
   * @return parsed value
   */
  T fromString(String value);
}
