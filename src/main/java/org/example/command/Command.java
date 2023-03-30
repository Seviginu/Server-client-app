package org.example.command;

interface Command {
    void execute(String[] args);
    String getDescription();
    String getName();
}
