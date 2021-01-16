package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;

import java.util.ArrayList;
import java.util.Date;

import exceptions.*;

public class MedicalPrescription {// A class that represents medical prescription
    private int prescCode;
    private Date prescDate;
    private Date endDate;
    private HealthCardID hcID; // the healthcard ID of the patient
    private DigitalSignature eSign; // the eSignature of the doctor
    private ArrayList<MedicalPrescriptionLine> mpl;
    private ArrayList<ProductID> prodPresc;
    private ProductID modifify;
    //??? // Its components, that is, the set of medical prescription lines

    public MedicalPrescription (int prescCode, Date prescDate, Date endDate, HealthCardID hcID, DigitalSignature eSign) {
        this.endDate=endDate;
        this.prescCode=prescCode;
        this.prescDate=prescDate;
        this.eSign=eSign;
        this.hcID=hcID;

        mpl = new ArrayList<>();
        prodPresc = new ArrayList<>();
    } // Makes some inicialization

    public void addLine(ProductID prodID, String[] instruc) throws IncorrectTakingGuidelinesException {
        if(instruc.length!=6) throw new IncorrectTakingGuidelinesException("Not valid Taking Guideline: instruction has more or less information.");
        mpl.add(new MedicalPrescriptionLine(prodID, new TakingGuideline(dayMoment.valueOf(instruc[0]), Float.valueOf(instruc[1]), instruc[2], Float.valueOf(instruc[3]), Float.valueOf(instruc[4]), FqUnit.valueOf(instruc[5]))));
        prodPresc.add(prodID);
    }
    public void modifyLine(ProductID prodID, String[] instruc) throws ProductNotInPrescription, IncorrectTakingGuidelinesException{
        if(!prodPresc.contains(prodID)) throw new ProductNotInPrescription("Product not in prescription.");
        if(instruc.length!=6)throw new IncorrectTakingGuidelinesException("Not valid Taking Guideline: instruction has more or less information.");

        for(int x = 0; x<= mpl.size(); x++){
            //modifify = new ProductID(mpl.get(x).getProduct().getUPC());

            if(prodID.equals(modifify)){
                mpl.get(x).setInstructions(new TakingGuideline(dayMoment.valueOf(instruc[0]), Float.valueOf(instruc[1]), instruc[2], Float.valueOf(instruc[3]), Float.valueOf(instruc[4]), FqUnit.valueOf(instruc[5])));
                break;
            }
        }
    }
    public void removeLine(ProductID prodID) throws ProductNotInPrescription {
        if(!prodPresc.contains(prodID))throw new ProductNotInPrescription("Product not in prescription.");
        for(int i = 0; i<= mpl.size(); i+=1){
            if(prodID.equals(mpl.get(i).getProduct())) {
                mpl.remove(i);
                prodPresc.remove(i);
                break;
            }
        }
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

    public ArrayList<MedicalPrescriptionLine> getPrescriptionLines() {
        return mpl;
    }

    public void seteSign(DigitalSignature eSign) {
        this.eSign = eSign;
    }
}