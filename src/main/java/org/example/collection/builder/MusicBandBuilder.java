package org.example.collection.builder;

import org.example.cli.*;
import org.example.collection.element.MusicBand;
import org.example.collection.exceptions.BuildException;

public class MusicBandBuilder extends Builder<MusicBand> {

    public MusicBandBuilder(UserInputChannel inputChannel, MusicBand element) {
        super(inputChannel, element);
    }

    public MusicBandBuilder(UserChannel channel, MusicBand element) {
        super(channel, element);
    }

    public void setName(){
        String value = inputChannel.getString();
        if(value.isEmpty()){
            ConsoleUserAsker<String> consoleUserAsker = new ConsoleUserAsker<>();
            value = consoleUserAsker.askUser(new StringValidator<>(){
                @Override
                public boolean validate(String value) {
                    return !value.isEmpty();
                }
                @Override
                public String fromString(String value) {
                    return value;
                }
                @Override
                public String getRequirements() {
                    return "Значние поля name не может быть пустым";
                }
            },
                    inputChannel, 3);
            if(value.isEmpty()) throw new BuildException();
        }
        element.setName(value);
    }



    public MusicBand getElement() {
        return element;
    }
}
