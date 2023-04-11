package org.example.command.exceptions;

public class CommandNotFoundException extends Exception{
    public CommandNotFoundException(){
        super("Комманда не найдена");
    }
}
