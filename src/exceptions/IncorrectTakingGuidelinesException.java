package exceptions;

public class IncorrectTakingGuidelinesException extends Exception {

    public static final long serialVersionUID = 700L;

    public IncorrectTakingGuidelinesException(String msg){
        super(msg);
    }

}
