package org.server.user;

import java.io.IOException;
import java.net.Socket;
import java.sql.*;

public class User {
    private Socket socket;


    public volatile int a = 0;
    public int b = 0;

    public User(){

    }
}
