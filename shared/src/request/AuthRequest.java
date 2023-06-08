package request;

public record AuthRequest(String username, byte[] password, boolean isNew) implements UserRequest {}
