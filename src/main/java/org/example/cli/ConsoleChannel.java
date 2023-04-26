package org.example.cli;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Scanner;

/** Simple console user channel. Gets and sends strings by default system input and output. */
public class ConsoleChannel implements UserChannel {
  private final Scanner scanner = new Scanner(System.in);

    /**
   * Returns the user's input string
   *
   * @return input string
   * @throws UncheckedIOException if channel is closed
   */
  @Override
  public String getString() {
    try {
      return scanner.nextLine();
    } catch (Exception e) {
      throw new UncheckedIOException(new IOException("Не удалось считать строку"));
    }
  }

  /**
   * Print the string with line terminated in standard system output
   *
   * @param string The string to be sent
   */
  @Override
  public void sendStringLine(String string) {
    System.out.println(string);
  }

  /**
   * Print the string in standard system output
   *
   * @param string The string to be sent
   */
  @Override
  public void sendString(String string) {
    System.out.print(string);
  }
}
