package org.example.collection.element;

import org.example.collection.Printable;

import java.util.Objects;

public class Coordinates extends Printable {
    private Double x; //Поле не может быть null
    private Double y; //Поле не может быть null


    @Override
    public int hashCode(){
        return Objects.hash(x, y);
    }
}
