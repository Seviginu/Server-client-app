package org.example.command;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.example.collection.MusicBandCollection;
import org.example.collection.element.MusicBand;

public class PrintUniqueParticipantsCommand extends CollectionCommand {

  public PrintUniqueParticipantsCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  @Override
  public void execute(List<String> args) {
    Set<Integer> numbersOfParticipants = new HashSet<>();
    for (MusicBand band : collection.getListOfElements())
      numbersOfParticipants.add(band.getNumberOfParticipants());
    for (Integer number : numbersOfParticipants) {
      manager.getOutputChannel().sendStringLine(number.toString());
    }
  }

  @Override
  public String getDescription() {
    return "Выводит уникальные значения numberOfParticipants всех элементов коллкции";
  }

  @Override
  public String getName() {
    return "print_unique_number_of_participants";
  }
}
