package org.example.collection;

import java.time.LocalDateTime;
import java.util.*;
import org.example.collection.element.MusicBand;

/** Class to contain and modify {@link List} of {@link MusicBand} objects */
public class MusicBandCollection {
  private LocalDateTime creationTime;
  private LocalDateTime updateTime;
  private final List<MusicBand> listOfElements;
  private static final Set<Long> idSet = new HashSet<>();

  public MusicBandCollection() {
    this.listOfElements = new LinkedList<>();
  }

  public MusicBandCollection(List<MusicBand> listOfElements) {
    this.listOfElements = listOfElements;
  }

  /**
   * Generates a unique id
   *
   * @return id
   */
  public static long generateId() {
    Random random = new Random();
    long newId;
    do {
      newId = Math.abs(random.nextLong());
    } while (idSet.contains(newId));
    return newId;
  }

  public void add(MusicBand band) {
    listOfElements.add(band);
    idSet.add(band.getId());
    updateTime = LocalDateTime.now();
  }

  public void clear() {
    listOfElements.clear();
    updateTime = LocalDateTime.now();
  }

  public LocalDateTime getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(LocalDateTime dateTime) {
    this.creationTime = dateTime;
  }

  public void setUpdateTime(LocalDateTime dateTime) {
    this.updateTime = dateTime;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public boolean remove(long id) {
    for (MusicBand musicBand : listOfElements) {
      if (musicBand.getId() == id) {
        listOfElements.remove(musicBand);
        updateTime = LocalDateTime.now();
        return true;
      }
    }
    return false;
  }

  public MusicBand removeByIndex(int index) {
    return listOfElements.remove(index);
  }

  public List<MusicBand> getListOfElements() {
    return new ArrayList<>(listOfElements);
  }

  public MusicBand getElement(Long id) {
    for (MusicBand band : listOfElements) {
      if (Objects.equals(band.getId(), id)) {
        return band;
      }
    }
    return null;
  }

  public boolean updateElement(Long id, MusicBand element) {
    for (MusicBand band : listOfElements) {
      if (Objects.equals(band.getId(), id)) {
        listOfElements.replaceAll(o -> o.getId().equals(id) ? element : o);
        updateTime = LocalDateTime.now();
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder
        .append("Creation time: ")
        .append(creationTime.toString())
        .append("\nUpdate time:")
        .append(updateTime.toString())
        .append("\n");
    for (MusicBand musicBand : listOfElements) {
      stringBuilder.append(musicBand).append("\n");
    }
    return stringBuilder.toString();
  }
}
