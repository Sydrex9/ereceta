package exceptions;

public class InvalidProductIDException extends Exception{

    public InvalidProductIDException(String errorMessage) {
        super(errorMessage);
    }
}
