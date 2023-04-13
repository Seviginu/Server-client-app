package org.example;

import org.example.cli.ConsoleChannel;
import org.example.command.CommandManager;
import org.example.command.HelpCommand;
import org.example.command.HistoryCommand;
import org.example.command.exceptions.CommandNotFoundException;

public class Main {
    public static void main(String[] args) {
        CommandManager manager = new CommandManager(new ConsoleChannel());
        manager.registerCommand(new HelpCommand(manager));
        manager.registerCommand(new HistoryCommand(manager));
        try{
            for (int i = 0; i < 20; ++i)
                manager.executeCommand("help");
            manager.executeCommand("history");
        }
        catch (CommandNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
}