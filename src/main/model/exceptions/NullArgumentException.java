package model.exceptions;

public class NullArgumentException extends IllegalArgumentException {
    public NullArgumentException() {
    }

    public NullArgumentException(String message) {
        super(message);
    }
}
