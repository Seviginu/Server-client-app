package org.example.collection.element;

import org.example.collection.Printable;

import java.lang.reflect.Field;
import java.util.Objects;

public class Person extends Printable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Float height; //Поле может быть null, Значение поля должно быть больше 0
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле не может быть null
    private Location location; //Поле не может быть null

    @Override
    public int hashCode(){
        return Objects.hash(name, height, hairColor, nationality, location);
    }
}