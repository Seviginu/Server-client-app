package org.example.collection.element;

import org.example.collection.Printable;

import java.util.Objects;

public class MusicBand extends Printable {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer numberOfParticipants; //Поле не может быть null, Значение поля должно быть больше 0
    private int albumsCount; //Значение поля должно быть больше 0
    private String description; //Поле может быть null
    private MusicGenre genre; //Поле может быть null
    private Person frontMan; //Поле не может быть null

    @Override
    public int hashCode(){
        return Objects.hash(
                id,
                name,
                coordinates,
                creationDate,
                numberOfParticipants,
                albumsCount,
                description,
                genre,
                frontMan);
    }
}