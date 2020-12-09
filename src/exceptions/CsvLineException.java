package exceptions;

public class CsvLineException extends IllegalArgumentException {
    public CsvLineException(String s) {
        super(s);
    }
}
