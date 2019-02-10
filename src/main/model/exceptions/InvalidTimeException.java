package model.exceptions;

public class InvalidTimeException extends Exception {
    public InvalidTimeException() {
    }

    public InvalidTimeException(String message) {
        super(message);
    }
}
