package org.server.cli;

import request.Request;
import org.server.request.RequestManager;
import request.RequestType;
import request.TextRequest;

import java.io.IOException;

public class NetworkUserChannel implements UserOutputChannel{
    private final RequestManager manager;

    public NetworkUserChannel(RequestManager manager){
        this.manager = manager;
    }

    @Override
    public void sendStringLine(String string) {
        try {
            manager.sendResponse(new TextRequest(string + "\n", RequestType.OK));
        }
         catch (IOException e){
            e.printStackTrace();
         }
    }

    @Override
    public void sendString(String string){
        try {
            manager.sendResponse(new TextRequest(string, RequestType.OK));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendResponse(Request<?> response) {
        try {
            manager.sendResponse(response);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
