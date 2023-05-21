package org.server.userIO;

import collection.builder.StringValidator;
import java.util.Scanner;

/**
 * {@code ConsoleUserAsker} is used if you need to get correct data from standard system input
 *
 * @param <T> Type of validating value
 */
public class ConsoleUserAsker<T> implements UserAsker<T> {
  /**
   * Method tries to get a correct input value from standard system input. If value entered by the
   * user is correct, returns value. Otherwise return {@code null}
   *
   * @param validator validator that checks the string entered by the user
   * @param attempts number of attempts the user can enter a value
   * @return return value if is correct. Otherwise {@code null}
   */
  @Override
  public T askUser(StringValidator<T> validator, int attempts) {
    for (int i = 0; i < attempts; ++i) {
      System.out.println("Вы неверно ввели значение");
      System.out.println(validator.getRequirements());
      System.out.print("Введите значение повторно\n> ");
      Scanner scanner = new Scanner(System.in);
      String value = scanner.nextLine();
      if (validator.validateString(value)) return validator.fromString(value);
    }
    return null;
  }
}
