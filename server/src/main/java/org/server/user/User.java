package org.server.user;

import java.io.IOException;
import java.net.Socket;
import java.sql.*;

public class User {
    private final Socket socket;



    public User(Socket socket){
        this.socket = socket;
    }
}
