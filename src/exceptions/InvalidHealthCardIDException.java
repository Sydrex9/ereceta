package exceptions;

public class InvalidHealthCardIDException extends Exception
{
    public InvalidHealthCardIDException(String errorMessage)
    {
        super(errorMessage);
    }
}
