package org.example.command;

interface Command {
    void execute();
    String getDescription();
    String getName();
}
