package org.example.cli;

import org.example.collection.builder.StringValidator;

/**
 * {@code UserAsker} is used if you need to get correct data from the user.
 *
 * @param <T> Type of validating value
 */
interface UserAsker<T> {
  /**
   * Method tries to get a correct input value from the user. If value entered by the user is
   * correct, returns value. Otherwise return {@code null}
   *
   * @param validator validator that checks the string entered by the user
   * @param attempts number of attempts the user can enter a value
   * @return return value if is correct. Otherwise {@code null}
   */
  T askUser(StringValidator<T> validator, int attempts);
}
