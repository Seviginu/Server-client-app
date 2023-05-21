package org.server.commands;

import collection.element.MusicBand;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.server.serverIO.RequestManager;

public class PrintUniqueParticipantsCommand extends CollectionCommand {

  public PrintUniqueParticipantsCommand(RequestManager requestManager, CommandManager manager) {
    super(requestManager, manager);
  }

  @Override
  public void execute(List<String> args) {
    Set<Integer> numbersOfParticipants = new HashSet<>();
    try {
      for (MusicBand band : requestManager.getCollection().getListOfElements())
        numbersOfParticipants.add(band.getNumberOfParticipants());
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
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
