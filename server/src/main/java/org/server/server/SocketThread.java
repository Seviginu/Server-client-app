package org.server.server;

import collection.MusicBandCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.server.command.CommandManager;
import org.server.database.CollectionData;
import parser.FileManager;
import request.CommandPackage;

import java.net.Socket;

public class SocketThread extends Thread{
    private final Socket socket;
    private final Logger logger = LogManager.getLogger("SocketThread");
    private final CommandManager commandManager;

    public SocketThread(Socket socket, CollectionData collectionData, MusicBandCollection collection){
        this.socket = socket;
        this.commandManager = new CommandManager(socket, collection, collectionData);
    }


    @Override
    public void run(){
        logger.info("user login");
        while (!socket.isClosed()) {
            CommandPackage commandPackage = Utils.getCommandPackage(socket);
            if(commandPackage == null) break;
            commandManager.executeCommand(commandPackage);
        }
        logger.info("user disconnect");
    }
}
