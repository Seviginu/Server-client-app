package collection;

import collection.builder.Validators;
import collection.element.MusicBand;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Class to contain and modify {@link List} of {@link MusicBand} objects */
public class MusicBandCollection implements Serializable {
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

    public MusicBandCollection(Object[] objects) {
      ArrayList<?> arrayList = new ArrayList<>(Arrays.stream(objects).toList());
      this.listOfElements = (List<MusicBand>) arrayList;
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

  /**
   * removes all items with invalid values or objects with same id
   *
   * @return count of removed items
   */
  public int removeInvalidElements() {
    HashSet<Long> idSet = new HashSet<>();
    int deleteItemsCount = 0;
    ArrayList<Integer> itemsToRemove = new ArrayList<>();
    for (int i = 0; i < listOfElements.size(); ++i) {
      if (idSet.contains(listOfElements.get(i).getId())
          || !Validators.getMusicBandValidator().validate(listOfElements.get(i))) {
        itemsToRemove.add(i);
        deleteItemsCount++;
      }
      idSet.add(listOfElements.get(i).getId());
    }
    int offset = 0;
    for (int itemId : itemsToRemove) {
      listOfElements.remove(itemId - offset++);
    }
    return deleteItemsCount;
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
    updateTime = LocalDateTime.now();
    return listOfElements.remove(index);
  }

  public List<MusicBand> getListOfElements() {
    return new ArrayList<>(listOfElements);
  }

  public MusicBand getElement(Long id) {
    return listOfElements.stream().filter(o -> id.equals(o.getId())).findFirst().orElse(null);
  }

  public boolean updateElement(Long id, MusicBand element) {
    element.setId(id);
    for (MusicBand band : listOfElements) {
      if (Objects.equals(band.getId(), id)) {
        element.setCreationDate(band.getCreationDate());
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
