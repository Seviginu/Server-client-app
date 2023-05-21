package IO;

/** Interface to receive string from user */
public interface UserOutputChannel {
  /**
   * Send string to user with terminated line
   *
   * @param string The string to be sent
   */
  void sendStringLine(String string);

  /**
   * Send string to user with terminated line
   *
   * @param string The string to be sent
   */
  void sendString(String string);
}
