package org.example.collection.builder;

import org.example.cli.UserChannel;
import org.example.cli.UserInputChannel;
import org.example.cli.UserOutputChannel;

public abstract class Builder<T> {
  protected T element;
  protected UserInputChannel inputChannel;
  protected UserOutputChannel outputChannel;
  protected boolean userMode;

  public Builder(UserInputChannel inputChannel, T element) {
    this.inputChannel = inputChannel;
    this.element = element;
    userMode = false;
  }

  public Builder(UserChannel channel, T element) {
    outputChannel = channel;
    inputChannel = channel;
    this.element = element;
    userMode = true;
  }

  public Builder(UserChannel channel) {
    outputChannel = channel;
    inputChannel = channel;
    userMode = true;
  }

  public Builder(UserInputChannel inputChannel, UserOutputChannel outputChannel, boolean userMode) {
    this.outputChannel = outputChannel;
    this.inputChannel = inputChannel;
    this.userMode = userMode;
  }

  abstract T getElement();
}
