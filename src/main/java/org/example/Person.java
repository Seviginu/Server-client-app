package org.example;

public class Person {
    private String name;
    private int age;
    private Address address;

    // конструкторы, геттеры и сеттеры

    // ...

    @Override
    public String toString(){
        return name + " " + age + " " + address;
    }
}
