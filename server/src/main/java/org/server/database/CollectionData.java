package org.server.database;

import collection.MusicBandCollection;
import collection.element.Entity;
import collection.element.EnumString;
import collection.element.Name;
import collection.element.Pk;
import utils.EnumFromString;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

public class CollectionData {
    Connection connection;

    public CollectionData(String url){
        Properties info = new Properties();
        try {
            info.load(new FileInputStream("db.cfg"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, info);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private String insertBuilder(Object o){
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO \"").append(o.getClass().getSimpleName()).append("\" VALUES (DEFAULT, ");

        for(Field field : o.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(Pk.class)) continue;

            builder.append("?, ");
        }
        builder.deleteCharAt(builder.length()-1).deleteCharAt(builder.length()-1);
        builder.append(");");
        return builder.toString();
    }

    public long saveObject(Object o){
        try {
            System.out.println(o);
            PreparedStatement statement = connection.prepareStatement(insertBuilder(o), Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            for (Field field : o.getClass().getDeclaredFields()){
                System.out.println(field.isEnumConstant());
                if(field.isAnnotationPresent(Pk.class)) continue;
                if (field.isAnnotationPresent(Entity.class)){
                    field.setAccessible(true);
                    long id =  saveObject(field.get(o));
                    statement.setLong(counter, id);
                }
                else if (field.isAnnotationPresent(EnumString.class)){
                    field.setAccessible(true);
                    statement.setString(counter, field.get(o).toString());
                }
                else{
                    field.setAccessible(true);
                    statement.setObject(counter, field.get(o));
                }
                counter++;
            }
            System.out.println(statement.toString());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next())
                return resultSet.getLong(1);
        }
        catch (SQLException | IllegalAccessException e){
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    private void setFields(Field[] fields, Object o, ResultSet resultSet) throws SQLException, IllegalAccessException, InstantiationException {
        for(Field field : fields){
            field.setAccessible(true);
            if(field.isAnnotationPresent(Pk.class)) continue;
            if(field.isAnnotationPresent(Entity.class)){
                field.set(o, loadObject(field, resultSet.getInt(field.getAnnotation(Name.class).name())));
            }
            else if(field.isAnnotationPresent(EnumString.class)){
                field.set(o, EnumFromString.get(resultSet.getString(field.getName())));
            }
            else{
                if(field.isAnnotationPresent(Name.class))
                    field.set(o, resultSet.getObject(field.getAnnotation(Name.class).name()));
                else {
                    if(field.getType().equals(LocalDateTime.class))
                        field.set(o, resultSet.getDate(field.getName()).toLocalDate().atStartOfDay());
                    else if (field.getType().equals(float.class) || field.getType().equals(Float.class))
                        field.set(o,  resultSet.getFloat(field.getName()));
                    else
                        field.set(o,  resultSet.getObject(field.getName()));
                }
            }
        }
    }

    private Object loadObject(Field field, long id) throws InstantiationException, IllegalAccessException, SQLException {
        System.out.println(field.getType());
        Object o = field.getType().newInstance();
        PreparedStatement statement =
                connection.prepareStatement(
                        "SELECT * FROM \"" + field.getType().getSimpleName() + "\" WHERE id=?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        setFields(field.getType().getDeclaredFields(), o, resultSet);
        return o;
    }

    public Object[] loadCollection(Class<?> clss) throws SQLException, IllegalAccessException, InstantiationException {
        ArrayList<Object> array = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \""  + clss.getSimpleName() + "\";");
        while(resultSet.next()){
            Object o = clss.newInstance();
            setFields(o.getClass().getDeclaredFields(), o, resultSet);
            array.add(o);
        }
        return array.toArray();
    }

    public void removeElement(Class<?> clss, long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE * FROM \""+clss.getSimpleName()+"\" WHERE id=?");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }
}
