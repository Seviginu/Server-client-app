package org.example.cli;

import org.example.collection.builder.StringValidator;

public class ConsoleUserAsker<T> implements UserAsker<T> {
    @Override
    public T askUser(StringValidator<T> validator, UserInputChannel input, int attempts) {
        for(int i = 0; i < attempts; ++i){
            System.out.println("Вы неверно ввели значение");
            System.out.println(validator.getRequirements());
            String value = input.getString();
            if(validator.validateString(value)) return validator.fromString(value);
        }
        return null;
    }
}
