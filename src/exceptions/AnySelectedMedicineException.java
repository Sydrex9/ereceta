package exceptions;

public class AnySelectedMedicineException extends Exception {

    public static final long serialVersionUID = 700L;

    public AnySelectedMedicineException(String msg){
        super(msg);
    }

}
