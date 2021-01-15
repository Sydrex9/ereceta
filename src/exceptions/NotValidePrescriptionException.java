package exceptions;

public class NotValidePrescriptionException extends Exception {

    public static final long serialVersionUID = 700L;

    public NotValidePrescriptionException(String msg){
        super(msg);
    }

}
