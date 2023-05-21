package IO;



/**
 * Combines {@link UserInputChannel} and {@link UserOutputChannel}. Can be used to exchange bytes
 * with the user in both ways.
 */
public interface UserChannel extends UserOutputChannel, UserInputChannel {}
