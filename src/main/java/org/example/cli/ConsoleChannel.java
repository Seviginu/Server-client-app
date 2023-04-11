package org.example.cli;

import java.util.Scanner;

public class ConsoleChannel implements UserChannel {
    private final Scanner scanner;

    public ConsoleChannel(Scanner scanner){
        this.scanner = scanner;
    }

    public ConsoleChannel(){
        scanner = new Scanner(System.in);
    }

    @Override
    public String getString() {
        return scanner.next();
    }

    @Override
    public void sendString(String string) {
        System.out.println(string);
    }
}
