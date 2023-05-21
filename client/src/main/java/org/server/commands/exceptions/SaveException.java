package org.server.commands.exceptions;

import java.io.IOException;
import java.io.UncheckedIOException;

public class SaveException extends UncheckedIOException {
  public SaveException(String message, IOException cause) {
    super(message, cause);
  }
}
