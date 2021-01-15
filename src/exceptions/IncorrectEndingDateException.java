package exceptions;

public class IncorrectEndingDateException extends Exception {

    public static final long serialVersionUID = 700L;

    public IncorrectEndingDateException(String msg){
        super(msg);
    }

}
