package org.example.cli;

import org.example.collection.builder.StringValidator;

interface UserAsker<T> {
    T askUser(StringValidator<T> validator, UserInputChannel input, int attempts);
}
