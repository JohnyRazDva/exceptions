package by.gsu.epamlab.exceptions;

public class IncorrectDataException extends IllegalArgumentException {
    public IncorrectDataException() {
        super();
    }

    public IncorrectDataException(String s) {
        super(s);
    }

    public IncorrectDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDataException(Throwable cause) {
        super(cause);
    }
}
