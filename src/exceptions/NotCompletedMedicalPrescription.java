package exceptions;

public class NotCompletedMedicalPrescription extends Exception {

    public static final long serialVersionUID = 700L;

    public NotCompletedMedicalPrescription(String msg){
        super(msg);
    }

}
