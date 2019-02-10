package parsers.exceptions;

public class ParsingException extends IllegalArgumentException {
    public ParsingException() {
    }

    public ParsingException(String s) {
        super(s);
    }
}
