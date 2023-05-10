package org.example.cli;

import org.example.request.GetObjectRequest;
import org.example.request.Request;
import org.example.request.RequestManager;
import org.example.request.RequestType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NetworkUserChannel implements UserChannel{
    private final RequestManager manager;

    public NetworkUserChannel(RequestManager manager){
        this.manager = manager;
    }

    @Override
    public String getString() {
        Request<?> request = manager.getRequest();
        if(request.getType() == RequestType.OK) return (String) request.getContent();
        return null;
    }

    @Override
    public void sendStringLine(String string) {
         manager.sendRequest(new GetObjectRequest<>(string + "\n", RequestType.OK));
    }

    @Override
    public void sendString(String string) {
        manager.sendRequest(new GetObjectRequest<>(string + "\n", RequestType.OK));
    }
}
