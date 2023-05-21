package org.server.commands;

import java.io.IOException;
import java.util.List;
import org.server.builder.MusicBandBuilder;
import org.server.commands.exceptions.WrongArgumentException;
import org.server.serverIO.RequestManager;
import request.CommandPackage;

public class UpdateCommand extends CollectionCommand {

  public UpdateCommand(RequestManager requestManager, CommandManager manager) {
    super(requestManager, manager);
  }

  @Override
  public void execute(List<String> args) {
    if (args.size() == 0) {
      throw new WrongArgumentException("Команда update должна содержать аргумент id");
    }
    long id;
    try {
      id = Long.parseLong(args.get(0));
    } catch (NumberFormatException e) {
      throw new WrongArgumentException("Аргумент id должен быть числом");
    }
    MusicBandBuilder builder =
        new MusicBandBuilder(
            manager.getInputChannel(), manager.getOutputChannel(), !args.contains("-nousermode"));
    try {
      requestManager.sendRequest(
          new CommandPackage(getName(), new Object[] {id, builder.getElement()}));
      manager.getOutputChannel().sendStringLine(requestManager.receiveMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getDescription() {
    return "Обновляет элемент с указанным id";
  }

  @Override
  public String getName() {
    return "update";
  }
}
