package by.gsu.epamlab.exceptions;

import by.gsu.epamlab.Byn;

public class NonPositiveValueException extends IncorrectDataException {
    public NonPositiveValueException() {
        super();
    }

    public NonPositiveValueException(String s) {
        super(s);
    }

    public NonPositiveValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonPositiveValueException(Throwable cause) {
        super(cause);
    }
}
