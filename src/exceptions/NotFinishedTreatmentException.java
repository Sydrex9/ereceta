package exceptions;

public class NotFinishedTreatmentException extends Exception {

    public static final long serialVersionUID = 700L;

    public NotFinishedTreatmentException(String msg){
        super(msg);
    }

}
