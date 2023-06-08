package org.server.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.*;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Utils {
    private static final Logger logger = LogManager.getLogger();

    public static CommandPackage getCommandPackage(Socket socket){
        try {

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectStream = new ObjectInputStream(inputStream);
            logger.debug("request received");
            CommandPackage result = (CommandPackage) objectStream.readObject();
            logger.trace(result);
            return result;
        } catch (Exception ignored) {
            logger.debug("fail during receive request");
            return null;
        }
    }

    public static AuthRequest getAuth(Socket socket){

        try {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectStream = new ObjectInputStream(inputStream);
            logger.debug("request received");
            AuthRequest result = (AuthRequest) objectStream.readObject();
            logger.trace(result);
            return result;
        } catch (Exception ignored) {
            logger.debug("fail during receive request");
            return null;
        }
    }

    public static void sendMessage(Socket socket, String message, RequestType type){
        try  {
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(outputStream);
            objectStream.writeObject(new TextRequest(message, type));
            logger.debug("send response");
            System.out.println(socket.isClosed());
        } catch (Exception e) {
            logger.error("fail during send response");
            e.printStackTrace();
        }
    }

    public static void sendResponse(Socket socket, Request<?> request){
        try  {
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(outputStream);
            objectStream.writeObject(request);
            logger.debug("send response");
            logger.trace(request);
        } catch (Exception e) {
            logger.error("fail during send response");
            e.printStackTrace();
        }
    }
}
