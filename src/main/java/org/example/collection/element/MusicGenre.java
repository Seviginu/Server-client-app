package org.example.collection.element;

public enum MusicGenre {
    ROCK("Рок"),
    RAP("Реп"),
    PSYCHEDELIC_CLOUD_RAP("Психоделичский клауд рэп"),
    SOUL("Соул"),
    POST_PUNK("Пост панк");

    private final String name;
    MusicGenre(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}