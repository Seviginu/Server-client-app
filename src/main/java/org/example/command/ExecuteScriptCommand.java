package org.example.command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import org.example.cli.IgnoreOutputChannel;
import org.example.cli.UserInputChannel;
import org.example.cli.UserOutputChannel;
import org.example.command.exceptions.WrongArgumentException;

public class ExecuteScriptCommand extends UserCommand {

  private static class ScriptFileUserChannel implements UserInputChannel {
    private Stack<BufferedReader> inputStreams;

    public void pushReader(BufferedReader reader) {
      inputStreams.push(reader);
    }

    public BufferedReader popReader() {
      return inputStreams.pop();
    }

    public boolean isEmpty() {
      return inputStreams.size() == 0;
    }

    @Override
    public String getString() {
      try {
        String string = inputStreams.firstElement().readLine();
        if (string == null) popReader().close();
        return string;
      } catch (IOException e) {
        return null;
      }
    }
  }

  public ExecuteScriptCommand(CommandManager manager) {
    super(manager);
  }

  private BufferedReader getReader(Path path) {
    try {
      return new BufferedReader(new FileReader(path.toFile()));
    } catch (IOException e) {
      return null;
    }
  }

  private void executionLoop(Set<Path> paths, ScriptFileUserChannel channel) {
    while (!channel.isEmpty()) {
      String string = channel.getString();
      if (string == null) continue;
      ArrayList<String> command = (ArrayList<String>) Arrays.stream(string.split(" ", 1)).toList();
      String commandName = command.remove(0);
      if (commandName.equals("execute_script")) {
        Path newPath = Paths.get(command.get(0)).toAbsolutePath();
        if (paths.contains(newPath)) continue;
        paths.add(newPath);
        channel.pushReader(getReader(newPath));
        continue;
      }
      try {
        manager.executeCommand(command.remove(0), command);
      } catch (Exception ignored) {
      }
    }
  }

  @Override
  public void execute(List<String> args) {
    if (args.size() == 0) throw new WrongArgumentException("Не указан путь до файла");
    Set<Path> paths = new HashSet<>();
    Path path = Paths.get(args.get(0)).toAbsolutePath();
    paths.add(path);
    BufferedReader reader = getReader(path);
    ScriptFileUserChannel channel = new ScriptFileUserChannel();
    channel.pushReader(reader);

    UserInputChannel previousInputChannel = manager.getInputChannel();
    UserOutputChannel previousOutputChannel = manager.getOutputChannel();
    manager.setOutputChannel(new IgnoreOutputChannel());
    manager.setInputChannel(channel);
    executionLoop(paths, channel);
    manager.setInputChannel(previousInputChannel);
    manager.setOutputChannel(previousOutputChannel);
  }

  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public String getName() {
    return null;
  }
}
