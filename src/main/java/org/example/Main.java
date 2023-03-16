package org.example;

import com.google.gson.Gson;
import org.example.collection.MusicBandCollection;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String json = "[{\"name\":\"John\",  \"address\":{\"street\":\"Main St\",\"city\":\"New York\",\"zip\":\"10001\"}}]";
        Gson gson = new Gson();
        ArrayList<Person> A = new ArrayList<>();
        MusicBandCollection person =

    }
}