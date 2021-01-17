package medicalconsultation;

import data.HealthCardID;
import data.DigitalSignature;
import services.ScheduledVisitAgenda;
import services.HealthNationalService;

import java.net.ConnectException;
import java.util.Date;
import java.util.List;

import exceptions.*;

public class ConsultationTerminal{

    private HealthCardID cip;
    private DigitalSignature signature;
    private MedicalPrescription prescription;
    private ProductSpecification specification;
    private HealthNationalService hns;
    private ScheduledVisitAgenda visitAgenda;
    private List<ProductSpecification> productsList;

    public ConsultationTerminal(HealthNationalService hns, ScheduledVisitAgenda va, DigitalSignature ds){

        this.hns = hns;
        this.visitAgenda = va;
        this.signature = ds;
    }

    private DigitalSignature digitalSignature;
    private HealthNationalService healthNationalService;
    private ScheduledVisitAgenda scheduledVisitAgenda;


    public ConsultationTerminal(DigitalSignature digitalSignature, HealthNationalService healthNationalService , ScheduledVisitAgenda scheduledVisitAgenda){

    }

    public void initRevision() throws HealthCardException, NotValidePrescriptionException, ConnectException {

        if(visitAgenda.getHealthCardID() == null){
            throw new HealthCardException("Healthcard id does not exist");
        }

        this.cip = visitAgenda.getHealthCardID();

        if(hns.getePrescription(cip) == null){
            throw new NotValidePrescriptionException("Not valid prescription");
        }

        this.prescription = hns.getePrescription(cip);

    }
    public void initPrescriptionEdition() throws AnyCurrentPrescriptionException, NotFinishedTreatmentException {

        if(prescription.equals(null)){
            throw new AnyCurrentPrescriptionException("No prescription in course");
        }
        if(new Date().before(prescription.getEndDate())){
            throw new AnyCurrentPrescriptionException("Not finished treatment");
        }
    }

    public void searchForProducts(String keyWord) throws AnyKeyWordMedicineException, ConnectException {

        if(hns.getProductsByKW(keyWord).isEmpty()) throw new AnyKeyWordMedicineException("No results for keyWord " + keyWord);
        this.productsList = hns.getProductsByKW(keyWord);


    }

    public void selectProduct(int option) throws AnyMedicineSearchException, ConnectException {

        if(productsList.isEmpty()) throw new AnyMedicineSearchException("No products in the list.");
        this.specification = hns.getProductSpecific(option);

    }

    public void enterMedicineGuidelines(String[] instruc) throws AnySelectedMedicineException, IncorrectTakingGuidelinesException {

        if (this.specification.equals(null)) throw new AnySelectedMedicineException("No product found");

        if(instruc.length != 6) throw new IncorrectTakingGuidelinesException("Incorrect instruction: has more or less information.");

        this.prescription.addLine(this.specification.getUPCCode(),instruc);

    }
    public void enterTreatmentEndingDate(Date date) throws IncorrectEndingDateException {
        if(date.before(new Date())) throw new IncorrectEndingDateException("Incorrect end date: end date before current date.");

        this.prescription.setPrescDate(new Date());
        this.prescription.setEndDate(date);
    }

    public void sendePrescription() throws ConnectException,NotValidePrescriptionException, eSignatureException, NotCompletedMedicalPrescription {

        if(this.signature.getDigitalSignatureCode().equals(null) || this.signature.getDigitalSignatureCode().length == 0 ) throw new eSignatureException("eSignature not correct.");

        this.prescription.seteSign(signature);

        if(this.prescription.getHcID().equals(null)) throw new NotValidePrescriptionException("health card id is not valid");

        if(this.prescription.getEndDate().equals(null) || this.prescription.getPrescDate().equals(null)
                || this.prescription.getHcID().equals(null) || this.prescription.geteSign().equals(null))
                throw new NotCompletedMedicalPrescription("Not completed medical prescription failure");

        //this.prescription = hns.sendePrescription(prescription);

    }

    public void printePresc() throws printingException {

        if(this.prescription == null) throw new printingException("Cannot print prescription cause of null prescription.");
        System.out.print(this.prescription.toString());

    }
}
