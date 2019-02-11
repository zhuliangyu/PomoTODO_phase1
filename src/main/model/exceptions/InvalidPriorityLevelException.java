package model.exceptions;

public class InvalidPriorityLevelException extends IllegalArgumentException {
//public class InvalidPriorityLevelException extends Exception {
    public InvalidPriorityLevelException() {
    }

    public InvalidPriorityLevelException(String message) {
        super(message);
    }
}
