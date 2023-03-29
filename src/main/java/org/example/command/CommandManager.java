package org.example.command;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CommandManager{

    private final HashMap<String, Command> commandsHashMap = new HashMap<>();

    public void registerCommand(Command command){
        commandsHashMap.put(command.getName(), command);
    }

    public Collection<Command> getListOfCommands(){
        return commandsHashMap.values();
    }
}
