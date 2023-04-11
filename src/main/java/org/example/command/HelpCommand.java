package org.example.command;

public class HelpCommand extends UserCommand{

    public HelpCommand(CommandManager manager) {
        super(manager);
    }

    @Override
    public void execute(String[] args) {
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
