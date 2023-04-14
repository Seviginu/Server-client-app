package org.example.collection.builder;

import org.example.cli.ConsoleUserAsker;
import org.example.cli.UserChannel;
import org.example.cli.UserInputChannel;
import org.example.cli.UserOutputChannel;
import org.example.collection.element.Color;
import org.example.collection.element.Country;
import org.example.collection.element.MusicGenre;
import org.example.collection.element.Person;
import org.example.collection.exceptions.BuildException;

public class PersonBuilder extends Builder<Person> {
    public PersonBuilder(UserInputChannel inputChannel, Person element) {
        super(inputChannel, element);
    }

    public PersonBuilder(UserChannel channel, Person element) {
        super(channel, element);
    }

    public PersonBuilder(UserInputChannel inputChannel, UserOutputChannel outputChannel, boolean userMode) {
        super(inputChannel, outputChannel, userMode);
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

    public void setHeight(){
        if(userMode) outputChannel.sendString("Введите значение поля height." +
                " Значение должно быть числом больше 0");
        String stringValue = inputChannel.getString();
        Float value;
        StringValidator<Float> validator = new StringValidator<>(){
            @Override
            public boolean validate(Float value) {
                return value != null && value > 0;
            }
            @Override
            public Float fromString(String value) {
                try {
                    return Float.parseFloat(value);
                }
                catch (Exception e){
                    return null;
                }
            }
            @Override
            public String getRequirements() {
                return "Значние height должно быть числом больше 0";
            }
        };
        if(!validator.validateString(stringValue)){
            ConsoleUserAsker<Float> consoleUserAsker = new ConsoleUserAsker<>();
            value = consoleUserAsker.askUser(validator, inputChannel, 3);
            if(!validator.validate(value)) throw new BuildException();
            else {
                element.setHeight(value);
                return;
            }
        }
        value = validator.fromString(stringValue);
        element.setHeight(value);
    }

    public void setHairColor(){
        if(userMode){
            outputChannel.sendString("Введите число для выбора Color:");
            int counter = 0;
            for(Color color : Color.values()) outputChannel.sendString(counter++ + ") " + color);
        }

        StringValidator<Color> validator = new StringValidator<>(){
            @Override
            public boolean validateString(String value) {
                return validate(fromString(value));
            }
            @Override
            public Color fromString(String value) {
                try{
                    int num = Integer.parseInt(value);
                    return Color.values()[num];
                }catch (Exception e){
                    return null;
                }
            }
            @Override
            public boolean validate(Color value) {
                return value != null;
            }
            @Override
            public String getRequirements() {
                return "Число должно быть в диапозоне 0-" + (Color.values().length-1);
            }
        };

        String stringValue = inputChannel.getString();

        if(!validator.validateString(stringValue)){
            ConsoleUserAsker<Color> consoleUserAsker = new ConsoleUserAsker<>();
            Color value = consoleUserAsker.askUser(validator, inputChannel, 3);
            if(!validator.validate(value)) throw new BuildException();
            else {
                element.setHairColor(value);
                return;
            }
        }
        element.setHairColor(validator.fromString(stringValue));
    }

    public void setNationality(){
        if(userMode){
            outputChannel.sendString("Введите число для выбора country:");
            int counter = 0;
            for(Country country : Country.values()) outputChannel.sendString(counter++ + ") " + country);
        }

        StringValidator<Country> validator = new StringValidator<>(){
            @Override
            public boolean validateString(String value) {
                return validate(fromString(value));
            }
            @Override
            public Country fromString(String value) {
                try{
                    int num = Integer.parseInt(value);
                    return Country.values()[num];
                }catch (Exception e){
                    return null;
                }
            }
            @Override
            public boolean validate(Country value) {
                return value != null;
            }
            @Override
            public String getRequirements() {
                return "Число должно быть в диапозоне 0-" + (MusicGenre.values().length-1);
            }
        };

        String stringValue = inputChannel.getString();

        if(!validator.validateString(stringValue)){
            ConsoleUserAsker<Country> consoleUserAsker = new ConsoleUserAsker<>();
            Country value = consoleUserAsker.askUser(validator, inputChannel, 3);
            if(!validator.validate(value)) throw new BuildException();
            else {
                element.setNationality(value);
                return;
            }
        }
        element.setNationality(validator.fromString(stringValue));
    }

    public void setLocation(){
        element.setLocation(new LocationBuilder(inputChannel, outputChannel, userMode).getElement());
    }

    @Override
    public Person getElement() {
        setName();
        setHeight();
        setHairColor();
        setNationality();
        setLocation();
        return element;
    }
}
