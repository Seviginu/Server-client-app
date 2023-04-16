package org.example.command;

import org.example.collection.MusicBandCollection;
import org.example.collection.element.MusicBand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GroupFrontManCommand extends CollectionCommand {
  public GroupFrontManCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  @Override
  public void execute(List<String> args) {
    ArrayList<MusicBand> bandsList = new ArrayList<>(collection.getListOfElements());
    bandsList.sort(Comparator.comparing(o -> o.getFrontMan().getName()));
    if (bandsList.size() == 0) return;
    int countOfGroups = 0;
    String frontManName = bandsList.get(0).getFrontMan().getName();
    for (MusicBand band : bandsList) {
      if (!Objects.equals(band.getFrontMan().getName(), frontManName)) {
        manager.getChannel().sendString(frontManName + ": " + countOfGroups + "\n");
        countOfGroups = 1;
        frontManName = band.getFrontMan().getName();
      }
      countOfGroups++;
      manager.getChannel().sendString(band.toString());
    }
    manager.getChannel().sendString(frontManName + ": " + countOfGroups + "\n");
  }

  @Override
  public String getDescription() {
    return "Группирует элементы коллекции по значению frontMan, выводит количество элементов в каждой группе";
  }

  @Override
  public String getName() {
    return "group_counting_by_front_man";
  }
}
