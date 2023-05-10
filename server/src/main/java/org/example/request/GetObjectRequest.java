package org.example.request;

public class GetObjectRequest<T> implements Request<T> {
    private final T obj;
    private final RequestType type;

    public GetObjectRequest(T obj, RequestType type){
        this.obj = obj;
        this.type = type;
    }

    @Override
    public T getContent() {
        return obj;
    }

    @Override
    public RequestType getType() {
        return type;
    }
}
