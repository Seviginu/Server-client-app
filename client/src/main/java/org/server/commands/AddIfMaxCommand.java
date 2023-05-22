package org.server.commands;

import collection.element.MusicBand;
import java.io.IOException;
import java.util.List;
import org.server.builder.MusicBandBuilder;
import org.server.serverIO.RequestManager;
import request.CommandPackage;

public class AddIfMaxCommand extends CollectionCommand {

  public AddIfMaxCommand(RequestManager requestManager, CommandManager manager) {
    super(requestManager, manager);
  }

  @Override
  public void execute(List<String> args) {
    MusicBandBuilder builder =
        new MusicBandBuilder(
            manager.getInputChannel(), manager.getOutputChannel(), !args.contains("-nousermode"));
    CommandPackage commandPackage =
        new CommandPackage(getName(), new MusicBand[] {builder.getElement()});
    try {
      requestManager.sendRequest(commandPackage);
      manager.getOutputChannel().sendStringLine(requestManager.receiveMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getDescription() {
    return "Добавляет элемент, если значение albumsCount больше остальных элементов коллекции";
  }

  @Override
  public String getName() {
    return "add_if_max";
  }
}