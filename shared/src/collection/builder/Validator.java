package collection.builder;

/**
 * Validates value of type {@code T}
 *
 * @param <T> type of validating value
 */
public interface Validator<T> {
  /**
   * @param value value to validate
   * @return {@code true} if value is correct and {@code false} if value is incorrect
   */
  boolean validate(T value);

  /**
   * Text representation of requirements to validate the value for user
   *
   * @return text with requirements
   */
  String getRequirements();
}
