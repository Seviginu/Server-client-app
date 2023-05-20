package org.server.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.server.cli.NetworkUserChannel;
import org.server.cli.UserInputChannel;
import org.server.cli.UserOutputChannel;
import collection.element.MusicBand;
import org.server.command.commands.Command;
import org.server.command.commands.ElementCommand;
import org.server.command.exceptions.CommandNotFoundException;
import request.CommandPackage;
import org.server.request.RequestManager;

public class CommandManager {

  private final HashMap<String, Command> commandsHashMap = new HashMap<>();
  private UserInputChannel inputChannel;
  private UserOutputChannel outputChannel;
  private final List<String> commandsHistory = new ArrayList<>();

  public CommandManager(RequestManager channel) {
    this.outputChannel = new NetworkUserChannel(channel);
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

  public void executeCommand(CommandPackage commandPackage){
    Command command = commandsHashMap.get(commandPackage.commandName());
    if (command instanceof ElementCommand){
      ((ElementCommand) command).setElement((MusicBand) commandPackage.args()[0]);
      if (commandPackage.args().length < 2) command.execute(List.of((String[]) commandPackage.args()));
    }
    if(commandPackage.args().length == 0) command.execute(new ArrayList<>());
    else command.execute(List.of((String[]) commandPackage.args()[0]));
  }

  public List<String> getCommandsHistory() {
    return commandsHistory;
  }

  public Collection<Command> getListOfCommands() {
    return commandsHashMap.values();
  }
}
