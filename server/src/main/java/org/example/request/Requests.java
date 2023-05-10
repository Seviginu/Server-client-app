package org.example.request;

public class Requests {
    public static Request<String> getErrorRequest(String message){
        return new Request<>() {
            @Override
            public String getContent() {
                return message;
            }

            @Override
            public RequestType getType() {
                return RequestType.ERROR;
            }
        };
    }
}
