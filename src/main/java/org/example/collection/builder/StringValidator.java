package org.example.collection.builder;

import org.example.collection.builder.Validator;

public interface StringValidator<T> extends Validator<T> {
    default boolean validate(String value){
        return validate(fromString(value));
    };
    T fromString(String value);
}
