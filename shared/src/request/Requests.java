package request;

public class Requests {
    public static Request<String> getErrorRequest(String message){
        return new Request<>() {
            @Override
            public String content() {
                return message;
            }

            @Override
            public RequestType type() {
                return RequestType.ERROR;
            }
        };
    }
}
