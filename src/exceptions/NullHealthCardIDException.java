package exceptions;

public class NullHealthCardIDException extends Exception
{
    public NullHealthCardIDException(String errorMessage)
    {
        super(errorMessage);
    }
}
