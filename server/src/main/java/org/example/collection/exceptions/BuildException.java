package org.example.collection.exceptions;

/** Thrown to indicate wrong input value during build */
public class BuildException extends RuntimeException {
  public BuildException() {
    super("Невозможно создать объект");
  }
}
