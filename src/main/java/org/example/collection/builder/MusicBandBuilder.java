package org.example.collection.builder;

import org.example.cli.*;
import org.example.collection.MusicBandCollection;
import org.example.collection.element.MusicBand;
import org.example.collection.element.MusicGenre;
import org.example.collection.exceptions.BuildException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;

public class MusicBandBuilder extends Builder<MusicBand> {

    public MusicBandBuilder(UserInputChannel inputChannel, MusicBand element) {
        super(inputChannel, element);
    }

    public MusicBandBuilder(UserChannel channel, MusicBand element) {
        super(channel, element);
    }

    public void setName(){
        if(userMode) outputChannel.sendString("Введите значение поля name." +
                " Значение не может быть пустым");
        String value = inputChannel.getString();
        StringValidator<String> validator = new StringValidator<>(){
            @Override
            public boolean validateString(String value) {
                return validate(value);
            }
            @Override
            public String fromString(String value) {
                return value;
            }
            @Override
            public boolean validate(String value) {
                return value != null && !value.isEmpty();
            }
            @Override
            public String getRequirements() {
                return "Значние name не может быть пустым";
            }
        };

        if(!validator.validateString(value)){
            ConsoleUserAsker<String> consoleUserAsker = new ConsoleUserAsker<>();
            value = consoleUserAsker.askUser(validator, inputChannel, 3);
            if(!validator.validate(value)) throw new BuildException();
            else {
                element.setName(value);
                return;
            }
        }
        element.setName(value);
    }

    public void setNumberOfParticipants(){
        if(userMode) outputChannel.sendString("Введите значение поля numberOfParticipants");
        String stringValue = inputChannel.getString();
        Integer value;
        StringValidator<Integer> validator = new StringValidator<>(){
            @Override
            public boolean validate(Integer value) {
                return value != null && value > 0;
            }
            @Override
            public Integer fromString(String value) {
                return Integer.parseInt(value);
            }
            @Override
            public String getRequirements() {
                return "Значние numberOfParticipants должно быть положительным";
            }
        };
        if(!validator.validateString(stringValue)){
            ConsoleUserAsker<Integer> consoleUserAsker = new ConsoleUserAsker<>();
            value = consoleUserAsker.askUser(validator, inputChannel, 3);
            if(!validator.validate(value)) throw new BuildException();
            else {
                element.setNumberOfParticipants(value);
                return;
            }
        }
        value = validator.fromString(stringValue);
        element.setNumberOfParticipants(value);
    }

    public void setAlbumsCount(){
        if(userMode) outputChannel.sendString("Введите значение поля numberOfParticipants." +
                " Значение должно быть положительным");
        String stringValue = inputChannel.getString();
        Integer value;
        StringValidator<Integer> validator = new StringValidator<>(){
            @Override
            public boolean validate(Integer value) {
                return value != null && value > 0;
            }
            @Override
            public Integer fromString(String value) {
                return Integer.parseInt(value);
            }
            @Override
            public String getRequirements() {
                return "Значние albumsCount не может быть пустым";
            }
        };
        if(!validator.validateString(stringValue)){
            ConsoleUserAsker<Integer> consoleUserAsker = new ConsoleUserAsker<>();
            value = consoleUserAsker.askUser(validator, inputChannel, 3);
            if(!validator.validate(value)) throw new BuildException();
            else {
                element.setAlbumsCount(value);
                return;
            }
        }
        value = validator.fromString(stringValue);
        element.setAlbumsCount(value);
    }

    public void setDescription(){
        if(userMode) outputChannel.sendString("Введите значение поля name." +
                " Значение не может быть пустым");
        String value = inputChannel.getString();
        StringValidator<String> validator = new StringValidator<>(){
            @Override
            public boolean validateString(String value) {
                return !value.isEmpty();
            }
            @Override
            public String fromString(String value) {
                return value;
            }
            @Override
            public boolean validate(String value) {
                return false;
            }
            @Override
            public String getRequirements() {
                return "Значние description не может быть пустым";
            }
        };
        if(!validator.validateString(value)){
            ConsoleUserAsker<String> consoleUserAsker = new ConsoleUserAsker<>();
            value = consoleUserAsker.askUser(validator, inputChannel, 3);
            if(!validator.validate(value)) throw new BuildException();
            else {
                element.setDescription(value);
                return;
            }
        }
        element.setDescription(value);
    }

    public void setGenre(){
        if(userMode){
            outputChannel.sendString("Введите число для выбора genre:");
            int counter = 0;
            for(MusicGenre genre : MusicGenre.values()) outputChannel.sendString(counter++ + ") " + genre);
        }

        StringValidator<MusicGenre> validator = new StringValidator<>(){
            @Override
            public boolean validateString(String value) {
                return validate(fromString(value));
            }
            @Override
            public MusicGenre fromString(String value) {
                try{
                    int num = Integer.parseInt(value);
                    return MusicGenre.values()[num];
                }catch (Exception e){
                    return null;
                }
            }
            @Override
            public boolean validate(MusicGenre value) {
                return value != null;
            }
            @Override
            public String getRequirements() {
                return "Число должно быть в диапозоне 0-" + (MusicGenre.values().length-1);
            }
        };

        String stringValue = inputChannel.getString();

        if(!validator.validateString(stringValue)){
            ConsoleUserAsker<MusicGenre> consoleUserAsker = new ConsoleUserAsker<>();
            MusicGenre value = consoleUserAsker.askUser(validator, inputChannel, 3);
            if(!validator.validate(value)) throw new BuildException();
            else {
                element.setGenre(value);
                return;
            }
        }
        element.setGenre(validator.fromString(stringValue));
    }

    public void setCoordinates(){
        element.setCoordinates(new CoordinatesBuilder(inputChannel, outputChannel, userMode).getElement());
    }

    public void setFrontMan(){
        element.setFrontMan(new PersonBuilder(inputChannel, outputChannel, userMode).getElement());
    }


    public void setId(){
        element.setId(new Random().nextLong());
    }

    public void setCreationDate(){
        LocalDateTime date = LocalDateTime.now();
        element.setCreationDate(date);
    }


    public MusicBand getElement() {
        setId();
        setName();
        setCoordinates();
        setNumberOfParticipants();
        setAlbumsCount();
        setDescription();
        setGenre();
        setFrontMan();
        setCreationDate();
        return element;
    }
}
