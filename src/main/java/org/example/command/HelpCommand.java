package org.example.command;

public class HelpCommand implements Command{
    private final CommandManager commandManager;

    HelpCommand(CommandManager commandManager){
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        for(Command command : commandManager.getListOfCommands()){
            System.out.println(command.getName() + ": " + command.getDescription() + "\n");
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
