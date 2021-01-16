package exceptions;

public class NullProductIDException extends Exception{

    public NullProductIDException(String errorMessage) {
        super(errorMessage);
    }
}
