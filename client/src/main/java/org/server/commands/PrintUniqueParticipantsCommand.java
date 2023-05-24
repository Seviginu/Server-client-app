package org.server.commands;

import collection.element.MusicBand;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.server.serverIO.RequestManager;
import utils.CommandNames;

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
      throw new UncheckedIOException(e);
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
    return CommandNames.PRINT_UNIQUE_PARTICIPANTS;
  }
}
