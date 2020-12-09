package exceptions;

public class NonPositiveArgument extends IllegalArgumentException {
    private int argumentValue;

    public NonPositiveArgument (String message, int argumentValue){
        super();
        this.argumentValue = argumentValue;
    }

    public int getArgumentValue() {
        return argumentValue;
    }
}
