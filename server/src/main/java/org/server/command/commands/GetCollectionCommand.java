package org.server.command.commands;

import collection.MusicBandCollection;
import java.io.IOException;
import java.util.List;

import org.server.cli.NetworkUserChannel;
import org.server.command.CommandManager;
import request.GetObjectRequest;
import request.RequestType;

public class GetCollectionCommand extends CollectionCommand {
  public GetCollectionCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  @Override
  public void execute(List<String> args) {
    NetworkUserChannel networkUserChannel = (NetworkUserChannel) manager.getOutputChannel();
    networkUserChannel.sendResponse(new GetObjectRequest<>(collection, RequestType.OK));
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
