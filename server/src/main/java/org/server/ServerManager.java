package org.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.server.cli.NetworkUserChannel;

import org.server.command.CommandManager;
import request.CommandPackage;
import org.server.request.RequestManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class ServerManager {
    private final static Logger logger =  LogManager.getLogger("org.server.ServerManager");

    public static void startServer(){
        try(ServerSocket server = new ServerSocket()) {
            server.bind(new InetSocketAddress(7566));
            RequestManager manager = new RequestManager(server);
            NetworkUserChannel channel = new NetworkUserChannel(manager);
            CommandManager commandManager = new CommandManager(manager);

            logger.info("Server ready");

            while (!server.isClosed()) {
                CommandPackage commandPackage = manager.getRequest();
                channel.sendStringLine("test");
                commandManager.executeCommand(commandPackage);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
