package org.example;

import java.util.Arrays;
import java.util.List;
import org.example.cli.UserChannel;
import org.example.command.CommandManager;

public class UserHandler {
  private final UserChannel channel;
  private final CommandManager manager;

  public UserHandler(UserChannel channel, CommandManager manager) {
    this.channel = channel;
    this.manager = manager;
  }

  public void startLoop() {
    channel.sendStringLine(
        "Программа готова к вводу команд. Введите help для просмотра списка команд");
    while (true) {

      channel.sendString(">> ");
      String inputString;
      inputString = channel.getString();
      List<String> message =
          new java.util.ArrayList<>(Arrays.stream(inputString.split(" ", 1)).toList());
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
