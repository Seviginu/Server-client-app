package org.example.collection;

import org.example.collection.element.MusicBand;
import org.example.collection.element.MusicGenre;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MusicBandCollection {
    private String creationTime;
    private String updateTime;
    private final List<MusicBand> listOfElements;


    public MusicBandCollection() {
        this.listOfElements = new LinkedList<MusicBand>();
    }

    public MusicBandCollection(List<MusicBand> listOfElements) {
        this.listOfElements = listOfElements;
    }

    public static long generateId(){
        Random random = new Random();
        return random.nextLong();
    }

    public void add(MusicBand band){
        listOfElements.add(band);
    }

    public void clear(){
        listOfElements.clear();
    }

    public String getCreationTime(){
        return creationTime;
    }

    public String getUpdateTime(){
        return updateTime;
    }

    public boolean remove(long id){
        for(MusicBand musicBand : listOfElements){
            if (musicBand.getId() == id){
                listOfElements.remove(musicBand);
                return true;
            }
        }
        return false;
    }

    public List<MusicBand> getListOfElements(){
        return new ArrayList<MusicBand>(listOfElements);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Creation time: ").append(creationTime)
                .append("\nUpdate time:").append(updateTime).append("\n");
        for(MusicBand musicBand : listOfElements){
            stringBuilder.append(musicBand).append("\n");
        }
        return stringBuilder.toString();
    }
}
