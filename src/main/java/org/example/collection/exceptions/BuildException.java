package org.example.collection.exceptions;

public class BuildException extends RuntimeException {
  public BuildException() {
    super("Невозможно создать объект");
  }
}
