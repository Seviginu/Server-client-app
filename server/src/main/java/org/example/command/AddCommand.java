package org.example.command;

import java.util.List;
import org.example.collection.MusicBandCollection;
import org.example.collection.builder.MusicBandBuilder;
import org.example.collection.element.MusicBand;
import org.example.request.Request;
import org.example.request.RequestType;

public class AddCommand extends CollectionCommand {
  public AddCommand(MusicBandCollection collection, CommandManager manager) {
    super(collection, manager);
  }

  @Override
  public void execute(List<String> args) {
    Request<?> request = manager.getRequestManager().getRequest();
    if(request.getType() == RequestType.ELEMENT){
      collection.add((MusicBand) request.getContent());
    }
  }

  @Override
  public String getDescription() {
    return "Добавляет элемент в коллекцию";
  }

  @Override
  public String getName() {
    return "add";
  }
}
