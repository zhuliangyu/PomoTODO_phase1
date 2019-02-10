package model.exceptions;

public class EmptyStringException extends Exception {

    public EmptyStringException() {
    }

    public EmptyStringException(String message) {
        super(message);
    }
}
