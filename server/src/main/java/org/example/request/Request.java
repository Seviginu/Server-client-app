package org.example.request;

public interface Request<T> {
    T getContent();

    RequestType getType();
}
