package model.exceptions;

public class NullArgumentException extends Exception {
    public NullArgumentException() {
    }

    public NullArgumentException(String message) {
        super(message);
    }
}
