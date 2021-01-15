package exceptions;

public class AnyMedicineSearchException extends Exception {

    public static final long serialVersionUID = 700L;

    public AnyMedicineSearchException(String msg){
        super(msg);
    }

}
