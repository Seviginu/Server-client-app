package org.example.collection.element;

import org.example.collection.Printable;

import java.util.Objects;

public class Location extends Printable {
    private long x;
    private float y;
    private Long z; //Поле не может быть null
    private String name; //Поле не может быть null


    @Override
    public int hashCode(){
        return Objects.hash(x, y, z, name);
    }
}