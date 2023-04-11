package org.example.command;

import java.util.List;

public class HelpCommand extends UserCommand{

    public HelpCommand(CommandManager manager) {
        super(manager);
    }

    @Override
    public void execute(List<String> args) {
        for(Command command : manager.getListOfCommands()){
            manager.getChannel().sendString(command.getName() + ": " + command.getDescription() + "\n");
        }
    }

    @Override
    public String getDescription() {
        return "Выводит список всех команд.";
    }

    @Override
    public String getName() {
        return "help";
    }
}
