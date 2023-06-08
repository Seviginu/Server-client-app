package request;

import java.io.Serializable;

public record CommandPackage(String commandName, Object[] args) implements UserRequest {}
