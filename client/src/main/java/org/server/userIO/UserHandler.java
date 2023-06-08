package org.server.userIO;

import IO.UserChannel;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import org.server.commands.CommandManager;
import org.server.serverIO.RequestManager;
import request.AuthRequest;
import request.Request;
import request.RequestType;

import javax.swing.*;

/** Class to start user loop and parse commands. */
public class UserHandler {
  private final UserChannel channel;
  private final CommandManager manager;
  private final RequestManager requestManager;

  public UserHandler(UserChannel channel, CommandManager manager, RequestManager requestManager) {
    this.channel = channel;
    this.manager = manager;
    this.requestManager = requestManager;
  }

  private byte[] toSHA256(String password){
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      return messageDigest.digest(password.getBytes());
    }
    catch (NoSuchAlgorithmException ignore){}
    return null;
  }

  private boolean login(String name, String password, boolean isNew) {
    AuthRequest request = new AuthRequest(name, toSHA256(password), isNew);
    try {
      requestManager.connect();
      requestManager.sendRequest(request);
      Request<?> response = requestManager.receiveResponse();
      channel.sendStringLine(response.content().toString());
      return response.type() == RequestType.OK;
    }
    catch (IOException e){
      e.printStackTrace();
    }
    return false;
  }

  private boolean auth(){
    channel.sendStringLine("Для работы с коллекцией необходима авторизация");
    channel.sendStringLine("1. Регистрация");
    channel.sendStringLine("2. Войти");
    String userInput = channel.getString();
    if(!(userInput.equals("1") || userInput.equals("2"))) return false;
    channel.sendString("Введите имя пользователя: ");
    String username = channel.getString();
    if(username.length() > 200){
      channel.sendStringLine("Слишком длинное имя пользователя");
      return false;
    }
    channel.sendString("Введите пароль: ");
    String password = channel.getString();
    if(userInput.equals("1")) return login(username, password, true);
    return login(username, password, false);
  }

  /** start user input loop */
  public void startLoop() {
    if(auth())
      channel.sendStringLine(
        "Программа готова к вводу команд. Введите help для просмотра списка команд");
    else return;
    while (true) {
      channel.sendString(">> ");
      String inputString;
      try {
        inputString = channel.getString();
      } catch (Exception e) {
        System.out.println("Невозможно считать строку");
        break;
      }
      List<String> message =
          new java.util.ArrayList<>(Arrays.stream(inputString.split(" ", 2)).toList());
      if (message.size() == 0) continue;
      String name = message.remove(0);
      if (name.equals("exit")) break;
      try {
        manager.executeCommand(name, message);
      } catch (Exception e) {
        e.printStackTrace();
        channel.sendStringLine(e.getMessage());
      }
    }
  }
}
