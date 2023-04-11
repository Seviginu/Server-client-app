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

    public void executeCommand(List<String> args) throws CommandNotFoundException {
        if (args.size() == 0) return;
        if (commandsHashMap.containsKey(args.get(0))) throw new CommandNotFoundException();
        String commandName = args.remove(0);
        commandsHistory.add(commandName);
        commandsHashMap.get(commandName).execute(args);
    }

    public List<String> getCommandsHistory(){
        return commandsHistory;
    }

    public Collection<Command> getListOfCommands(){
        return commandsHashMap.values();
    }
}
