package org.example.command;

import org.example.cli.UserChannel;
import org.example.command.exceptions.CommandNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CommandManager{

    private final HashMap<String, Command> commandsHashMap = new HashMap<>();
    private final UserChannel channel;
    private final List<String> commandsHistory;

    public CommandManager(UserChannel channel) {
        this.channel = channel;
        commandsHistory = new ArrayList<>();
    }

    public UserChannel getChannel(){
        return channel;
    }

    public void registerCommand(Command command){
        commandsHashMap.put(command.getName(), command);
    }

    public void executeCommand(String commandName, List<String> args) throws CommandNotFoundException {
        if (!commandsHashMap.containsKey(commandName))throw new CommandNotFoundException();
        commandsHashMap.get(commandName).execute(args);
        commandsHistory.add(commandName);
    }

    public void executeCommand(String commandName) throws CommandNotFoundException {
        executeCommand(commandName, new ArrayList<>());
    }

    public List<String> getCommandsHistory(){
        return commandsHistory;
    }

    public Collection<Command> getListOfCommands(){
        return commandsHashMap.values();
    }
}
