package org.example.collection;

import org.example.collection.element.MusicBand;
import org.example.collection.element.MusicGenre;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class MusicBandCollection {
    private String creationTime;
    private String updateTime;


    private List<MusicBand> listOfElements;


    public MusicBandCollection() {

        this.listOfElements = new LinkedList<MusicBand>();

    }

    public MusicBandCollection(List<MusicBand> listOfElements) {
        this.listOfElements = listOfElements;
    }
}
