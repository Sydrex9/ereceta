package exceptions;

public class AnyCurrentPrescriptionException extends Exception {

    public static final long serialVersionUID = 700L;

    public AnyCurrentPrescriptionException(String msg){
        super(msg);
    }

}
