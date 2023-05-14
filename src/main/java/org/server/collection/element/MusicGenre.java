package org.server.collection.element;

public enum MusicGenre {
  ROCK("Рок"),
  RAP("Рэп"),
  PSYCHEDELIC_CLOUD_RAP("Психоделический клауд рэп"),
  SOUL("Соул"),
  POST_PUNK("Пост панк");

  private final String name;

  MusicGenre(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
