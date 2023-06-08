package utils;

import collection.element.Color;
import collection.element.Country;
import collection.element.MusicGenre;

import java.util.HashMap;

public class EnumFromString {
    private static final HashMap<String, Object> hm = new HashMap<>();

    static {
        hm.put(Color.RED.toString(), Color.RED);
        hm.put(Color.BLACK.toString(), Color.BLACK);
        hm.put(Color.YELLOW.toString(), Color.YELLOW);
        hm.put(Country.USA.toString(), Country.USA);
        hm.put(Country.ITALY.toString(), Country.ITALY);
        hm.put(Country.JAPAN.toString(), Country.JAPAN);
        hm.put(Country.RUSSIA.toString(), Country.RUSSIA);
        hm.put(Country.SOUTH_KOREA.toString(), Country.SOUTH_KOREA);
        hm.put(MusicGenre.RAP.toString(), MusicGenre.RAP);
        hm.put(MusicGenre.SOUL.toString(), MusicGenre.SOUL);
        hm.put(MusicGenre.ROCK.toString(), MusicGenre.ROCK);
        hm.put(MusicGenre.POST_PUNK.toString(), MusicGenre.POST_PUNK);
        hm.put(MusicGenre.PSYCHEDELIC_CLOUD_RAP.toString(), MusicGenre.PSYCHEDELIC_CLOUD_RAP);
    }
    static public Object get(String string){
        return hm.get(string);
    }
}
