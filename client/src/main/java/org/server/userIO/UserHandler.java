package org.server.userIO;

import IO.UserChannel;
import java.util.Arrays;
import java.util.List;
import org.server.commands.CommandManager;

/** Class to start user loop and parse commands. */
public class UserHandler {
  private final UserChannel channel;
  private final CommandManager manager;

  public UserHandler(UserChannel channel, CommandManager manager) {
    this.channel = channel;
    this.manager = manager;
  }

  /** start user input loop */
  public void startLoop() {
    channel.sendStringLine(
        "Программа готова к вводу команд. Введите help для просмотра списка команд");
    while (true) {
      channel.sendString(">> ");
      String inputString = null;
      try {
        inputString = channel.getString();
      } catch (Exception e) {
        System.out.println("Невозможно считать строку");
        break;
      }
      List<String> message =
          new java.util.ArrayList<>(Arrays.stream(inputString.split(" ", 2)).toList());
      if (message.size() == 0) continue;
      String name = message.remove(0);
      if (name.equals("exit")) break;
      try {
        manager.executeCommand(name, message);
      } catch (Exception e) {
        channel.sendStringLine(e.getMessage());
      }
    }
  }
}
