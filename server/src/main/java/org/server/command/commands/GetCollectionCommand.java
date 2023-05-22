package org.server.command.commands;

import collection.MusicBandCollection;
import java.io.IOException;
import java.util.List;
import org.server.command.CommandManager;
import request.GetObjectRequest;
import request.RequestType;

public class GetCollectionCommand extends CollectionCommand {
  public GetCollectionCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  @Override
  public void execute(List<String> args) {
    try {

      manager
          .getRequestManager()
          .sendResponse(new GetObjectRequest<MusicBandCollection>(collection, RequestType.OK));
    } catch (IOException e) {
      logger.error(e);
    }
  }

  @Override
  public String getDescription() {
    return "возвращает на клиент экземпляр MusicBandCollection";
  }

  @Override
  public String getName() {
    return "get_collection";
  }
}
