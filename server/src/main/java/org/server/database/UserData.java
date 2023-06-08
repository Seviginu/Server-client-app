package org.server.database;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class UserData {
    private Connection connection;

    public UserData(String url, Properties info){
        try{
            this.connection = DriverManager.getConnection(url, info);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static PreparedStatement getStatement(String query){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "Zk140803");
            return connection.prepareStatement(
                    query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkExistence(String username){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM users WHERE users.name = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getInt(1));
            return  resultSet.getInt(1) >= 1;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean Login(String name, byte[] password) throws IOException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM users WHERE users.name = ? AND users.pass = ?");
            statement.setString(1, name);
            statement.setBytes(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() && resultSet.getInt(1) >= 1;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public boolean Register(String name, byte[] password) throws IOException {
        try {
            if(checkExistence(name)) return false;
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users(name, pass) VALUES (?, ?)");
            statement.setString(1, name);
            statement.setBytes(2, password);
            statement.execute();
            statement.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

}
