package request;

public class Requests {
    public static final String GET_COLLECTION_REQUEST = "get_collection";

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
