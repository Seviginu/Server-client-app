package org.server.commands;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import org.server.serverIO.RequestManager;
import request.CommandPackage;

public class RemoveByIdCommand extends CollectionCommand {

  public RemoveByIdCommand(RequestManager requestManager, CommandManager manager) {
    super(requestManager, manager);
  }

  @Override
  public void execute(List<String> args) {
    if (args.isEmpty()) {
      manager.getOutputChannel().sendStringLine("Не указан id");
      return;
    }
    try {
      manager
          .getOutputChannel()
          .sendStringLine(
              requestManager.sendTextRequest(
                  (new CommandPackage(getName(), new Long[] {Long.parseLong(args.get(0))}))));

    } catch (NumberFormatException e) {
      manager.getOutputChannel().sendStringLine("id должен быть числом");
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @Override
  public String getDescription() {
    return "Удаляет элемент по id";
  }

  @Override
  public String getName() {
    return "remove_by_id";
  }
}
