package org.example.cli;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Scanner;

public class ConsoleChannel implements UserChannel {
  private Scanner scanner;

  public ConsoleChannel() {
    scanner = new Scanner(System.in);
  }

  @Override
  public String getString() {
    try{
      return scanner.nextLine();
    }
    catch (Exception e){
      throw new UncheckedIOException(new IOException("Не удалось считать строку"));
    }
  }

  @Override
  public void sendStringLine(String string) {
    System.out.println(string);
  }

  @Override
  public void sendString(String string) {
    System.out.print(string);
  }
}
