package org.example.collection.builder;

public interface Validator<T> {
  boolean validate(T value);

  String getRequirements();
}
