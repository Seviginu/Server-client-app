package org.example.collection.builder;

import org.example.cli.UserChannel;
import org.example.cli.UserInputChannel;
import org.example.cli.UserOutputChannel;

/**
 * build an object of type {@code T} from {@link UserInputChannel}.
 *
 * @param <T> Type of building object
 */
public abstract class Builder<T> {
  protected T element;
  protected UserInputChannel inputChannel;
  protected UserOutputChannel outputChannel;
  protected boolean userMode;

  /**
   * Creates a builder with {@link UserInputChannel}. If builder was created by this constructor
   * user mode will be disabled by default. This means that all messages prompting user for input
   * will be suppressed.
   *
   * @param inputChannel channel to get user input
   * @param element element to rebuild(update)
   */
  public Builder(UserInputChannel inputChannel, T element) {
    this.inputChannel = inputChannel;
    this.element = element;
    userMode = false;
  }

  /**
   * Creates a builder with {@link UserChannel}.
   *
   * @param channel user input/output channel
   * @param element element to rebuild(update)
   */
  public Builder(UserChannel channel, T element) {
    outputChannel = channel;
    inputChannel = channel;
    this.element = element;
    userMode = true;
  }

  /**
   * Creates a builder with I/O channels and {@code userMode}. If {@code usedMode=false} all
   * messages for user will be suppressed
   *
   * @param inputChannel channel to get user input
   * @param outputChannel channel to send prompting information
   * @param userMode if {@code userMode=false} suppress all output messages
   */
  public Builder(UserInputChannel inputChannel, UserOutputChannel outputChannel, boolean userMode) {
    this.outputChannel = outputChannel;
    this.inputChannel = inputChannel;
    this.userMode = userMode;
  }

  /**
   * Start builds element
   *
   * @return Created element
   */
  abstract T getElement();
}
