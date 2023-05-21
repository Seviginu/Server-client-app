package org.server.commands;

import IO.UserInputChannel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import org.server.commands.exceptions.WrongArgumentException;

public class ExecuteScriptCommand extends UserCommand {

  private static class ScriptFileUserChannel implements UserInputChannel {
    private final Stack<BufferedReader> inputStreams = new Stack<>();

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
        String string = inputStreams.lastElement().readLine();
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

  private void executionLoop(Path path, Set<Path> paths, ScriptFileUserChannel channel) {
    while (!channel.isEmpty()) {
      String string = channel.getString();
      if (string == null) continue;
      List<String> command = new ArrayList<>(Arrays.asList(string.split(" ", 2)));
      String commandName = command.get(0);
      if (commandName.equals("execute_script")) {
        Path newPath = path.resolve(command.get(1));
        if (paths.contains(newPath)) continue;
        paths.add(newPath);
        BufferedReader reader = getReader(newPath);
        if (reader != null) channel.pushReader(reader);
        continue;
      }
      try {
        command.add("-nousermode");
        manager.executeCommand(command.remove(0), command);
      } catch (Exception ignored) {
      }
    }
  }

  private boolean valideFile(String filePath) {
    return new File(filePath).canRead();
  }

  @Override
  public void execute(List<String> args) {
    if (args.size() == 0) throw new WrongArgumentException("Не указан путь до файла");
    if (!valideFile(args.get(0)))
      throw new WrongArgumentException("Невозможно прочитать файл скрипта");
    Set<Path> paths = new HashSet<>();
    Path path = Paths.get(args.get(0)).toAbsolutePath();
    paths.add(path);
    BufferedReader reader = getReader(path);
    ScriptFileUserChannel channel = new ScriptFileUserChannel();
    channel.pushReader(reader);

    UserInputChannel previousInputChannel = manager.getInputChannel();
    manager.setInputChannel(channel);
    executionLoop(path, paths, channel);
    manager.setInputChannel(previousInputChannel);
  }

  @Override
  public String getDescription() {
    return "Исполняет скрипт, указанный в файле";
  }

  @Override
  public String getName() {
    return "execute_script";
  }
}
