package org.example.collection;

import org.example.collection.element.MusicBand;
import org.example.collection.element.MusicGenre;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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

    public String getCreationTime(){
        return creationTime;
    }

    public String getUpdateTime(){
        return updateTime;
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

    public void clear(){
        listOfElements.clear();
    }

    public List<MusicBand> getListOfElements(){
        return listOfElements;
    }
}
