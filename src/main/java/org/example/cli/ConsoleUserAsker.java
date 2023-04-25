package org.example.cli;

import java.util.Scanner;
import org.example.collection.builder.StringValidator;

public class ConsoleUserAsker<T> implements UserAsker<T> {
  @Override
  public T askUser(StringValidator<T> validator, UserInputChannel input, int attempts) {
    for (int i = 0; i < attempts; ++i) {
      System.out.println("Вы неверно ввели значение");
      System.out.println(validator.getRequirements());
      Scanner scanner = new Scanner(System.in);
      String value = scanner.nextLine();
      if (validator.validateString(value)) return validator.fromString(value);
    }
    return null;
  }
}
