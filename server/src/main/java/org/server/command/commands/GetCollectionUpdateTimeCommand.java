package org.server.command.commands;

import collection.MusicBandCollection;
import java.util.List;
import org.server.cli.NetworkUserChannel;
import org.server.command.CommandManager;
import request.GetObjectRequest;
import request.RequestType;
import utils.CommandNames;

public class GetCollectionUpdateTimeCommand extends CollectionCommand {
  public GetCollectionUpdateTimeCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  @Override
  public void execute(List<String> args) {
    NetworkUserChannel networkUserChannel = (NetworkUserChannel) manager.getOutputChannel();
    networkUserChannel.sendResponse(
        new GetObjectRequest<>(collection.getUpdateTime(), RequestType.OK));
  }

  @Override
  public String getDescription() {
    return "возвращает время последнего изменения в коллекции";
  }

  @Override
  public String getName() {
    return CommandNames.GET_COLLECTION_UPDATE_TIME;
  }
}
