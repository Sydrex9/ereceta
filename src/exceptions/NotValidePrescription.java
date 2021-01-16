package exceptions;

public class NotValidePrescription extends Exception {

    public static final long serialVersionUID = 700L;

    public NotValidePrescription(String msg){
        super(msg);
    }

}
