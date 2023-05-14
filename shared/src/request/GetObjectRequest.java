package request;

public class GetObjectRequest<T> implements Request<T> {
    private final T obj;
    private final RequestType type;

    public GetObjectRequest(T obj, RequestType type){
        this.obj = obj;
        this.type = type;
    }

    @Override
    public T content() {
        return obj;
    }

    @Override
    public RequestType type() {
        return type;
    }
}
