package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;

import java.util.*;

import exceptions.*;

public class MedicalPrescription {// A class that represents medical prescription
    private int prescCode;
    private Date prescDate;
    private Date endDate;
    private HealthCardID hcID; // the healthcard ID of the patient
    private DigitalSignature eSign; // the eSignature of the doctor
    // Its components, that is, the set of medical prescription lines
    private HashMap<ProductID, MedicalPrescriptionLine> mpl;


    public MedicalPrescription (int prescCode, Date prescDate, Date endDate, HealthCardID hcID, DigitalSignature eSign) {
        this.endDate=endDate;
        this.prescCode=prescCode;
        this.prescDate=prescDate;
        this.eSign=eSign;
        this.hcID=hcID;

        mpl = new HashMap<>();
    } // Makes some inicialization

    public void addLine(ProductID prodID, String[] instruc) throws IncorrectTakingGuidelinesException {
        if(instruc.length!=6) throw new IncorrectTakingGuidelinesException("Not valid Taking Guideline: instruction has more or less information.");
        mpl.put(prodID, new MedicalPrescriptionLine(prodID, new TakingGuideline(dayMoment.valueOf(instruc[0]), Float.valueOf(instruc[1]), instruc[2], Float.valueOf(instruc[3]), Float.valueOf(instruc[4]), FqUnit.valueOf(instruc[5]))));
    }
    public void modifyLine(ProductID prodID, String[] instruc) throws ProductNotInPrescription, IncorrectTakingGuidelinesException, InvalidProductIDException, NullProductIDException {
        Set<ProductID> key_mpl = mpl.keySet();
        Iterator<ProductID> itProdID = key_mpl.iterator();
        if(!mpl.containsKey(prodID)) throw new ProductNotInPrescription("Product not in prescription.");
        if(instruc.length!=6)throw new IncorrectTakingGuidelinesException("Not valid Taking Guideline: instruction has more or less information.");

        while(itProdID.hasNext()){

            ProductID next_mpl = itProdID.next();

            if(prodID.equals(next_mpl)){
                mpl.get(next_mpl).setInstructions(new TakingGuideline(dayMoment.valueOf(instruc[0]), Float.valueOf(instruc[1]), instruc[2], Float.valueOf(instruc[3]), Float.valueOf(instruc[4]), FqUnit.valueOf(instruc[5])));
                break;
            }

        }
    }
    public void removeLine(ProductID prodID) throws ProductNotInPrescription {
        if(!mpl.containsKey(prodID)) throw new ProductNotInPrescription("Product not in prescription.");
        mpl.remove(prodID);

    }


    public Date getPrescDate() {
        return prescDate;
    }

    public int getPrescCode() {
        return prescCode;
    }

    public HealthCardID getHcID() {
        return hcID;
    }

    public Date getEndDate() {
        return endDate;
    }

    public DigitalSignature geteSign() {
        return eSign;
    }

    public void setPrescCode(int prescCode) {
        this.prescCode = prescCode;
    }

    public void setPrescDate(Date prescDate) {
        this.prescDate = prescDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setHcID(HealthCardID hcID) {
        this.hcID = hcID;
    }

    public HashMap<ProductID, MedicalPrescriptionLine> getPrescriptionLines() {
        return mpl;
    }

    public void seteSign(DigitalSignature eSign) {
        this.eSign = eSign;
    }
}