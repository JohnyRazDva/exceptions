package by.gsu.epamlab.exceptions;

public class IncorrectDiscountValue extends IncorrectDataException {
    public IncorrectDiscountValue() {
        super();
    }

    public IncorrectDiscountValue(String s) {
        super(s);
    }

    public IncorrectDiscountValue(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDiscountValue(Throwable cause) {
        super(cause);
    }
}
