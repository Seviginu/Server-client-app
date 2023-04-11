package org.example.command;

import org.example.cli.UserChannel;

import java.util.Collection;
import java.util.HashMap;

public class CommandManager{

    private final HashMap<String, Command> commandsHashMap = new HashMap<>();
    private final UserChannel channel;

    public CommandManager(UserChannel channel) {
        this.channel = channel;
    }

    public UserChannel getChannel(){
        return channel;
    }

    public void registerCommand(Command command){
        commandsHashMap.put(command.getName(), command);
    }

    public Collection<Command> getListOfCommands(){
        return commandsHashMap.values();
    }
}
