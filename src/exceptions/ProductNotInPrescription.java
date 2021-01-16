package exceptions;

public class ProductNotInPrescription extends Exception {

    public static final long serialVersionUID = 700L;

    public ProductNotInPrescription(String msg){
        super(msg);
    }

}
