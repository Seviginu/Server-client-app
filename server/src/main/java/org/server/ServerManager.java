package org.server;

import org.server.cli.NetworkUserChannel;

import org.server.command.CommandManager;
import request.CommandPackage;
import request.RequestManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class ServerManager {
    public static void startServer(){
        try(ServerSocket server = new ServerSocket()) {
            server.bind(new InetSocketAddress(7566));
            RequestManager manager = new RequestManager(server);
            NetworkUserChannel channel = new NetworkUserChannel(manager);
            CommandManager commandManager = new CommandManager(manager);
            while (!server.isClosed()) {
                CommandPackage commandPackage = manager.getRequest();
                channel.sendStringLine("test");
                if(commandPackage != null){
                    commandManager.executeCommand(commandPackage);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
