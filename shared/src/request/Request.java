package request;

import java.io.Serializable;

public interface Request<T> extends Serializable {
    T content();

    RequestType type();
}
