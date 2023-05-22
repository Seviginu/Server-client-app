package request;

public record TextRequest(String content, RequestType type) implements Request<String> {}
