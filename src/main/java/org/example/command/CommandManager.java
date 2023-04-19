package org.example.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.example.cli.UserChannel;
import org.example.cli.UserInputChannel;
import org.example.cli.UserOutputChannel;
import org.example.command.exceptions.CommandNotFoundException;

public class CommandManager {

  private final HashMap<String, Command> commandsHashMap = new HashMap<>();
  private UserInputChannel inputChannel;
  private UserOutputChannel outputChannel;
  private final List<String> commandsHistory = new ArrayList<>();

  public CommandManager(UserChannel channel) {
    this.outputChannel = channel;
    this.inputChannel = channel;
  }

  public UserOutputChannel getOutputChannel() {
    return outputChannel;
  }

  public UserInputChannel getInputChannel() {
    return inputChannel;
  }

  public void setOutputChannel(UserOutputChannel outputChannel) {
    this.outputChannel = outputChannel;
  }

  public void setInputChannel(UserInputChannel inputChannel) {
    this.inputChannel = inputChannel;
  }

  public void registerCommand(Command command) {
    commandsHashMap.put(command.getName(), command);
  }

  public void executeCommand(String commandName, List<String> args)
      throws CommandNotFoundException {
    if (!commandsHashMap.containsKey(commandName)) throw new CommandNotFoundException();
    commandsHashMap.get(commandName).execute(args);
    commandsHistory.add(commandName);
  }

  public void executeCommand(String commandName) throws CommandNotFoundException {
    executeCommand(commandName, new ArrayList<>());
  }

  public List<String> getCommandsHistory() {
    return commandsHistory;
  }

  public Collection<Command> getListOfCommands() {
    return commandsHashMap.values();
  }
}
