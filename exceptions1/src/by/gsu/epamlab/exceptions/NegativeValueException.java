package by.gsu.epamlab.exceptions;

public class NegativeValueException extends IncorrectDataException {
    public NegativeValueException() {
        super();
    }

    public NegativeValueException(String s) {
        super(s);
    }

    public NegativeValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeValueException(Throwable cause) {
        super(cause);
    }
}
