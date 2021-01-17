package medicalconsultation;

import data.HealthCardID;
import data.DigitalSignature;
import services.ScheduledVisitAgenda;
import services.HealthNationalService;

import java.net.ConnectException;
import java.util.Date;
import java.util.List;

import exceptions.*;

public class ConsultationTerminal implements ScheduledVisitAgenda, HealthNationalService{

    private HealthCardID CIP;
    private DigitalSignature signature;
    private MedicalPrescription prescription;
    private ProductSpecification specification;
    private HealthNationalService HNS;
    private ScheduledVisitAgenda visitAgenda;
    private List<ProductSpecification> productsList;

    public ConsultationTerminal(HealthNationalService hns, ScheduledVisitAgenda va, DigitalSignature ds){

        this.HNS = hns;
        this.visitAgenda = va;
        this.signature = ds;
    }

    public void initRevision() throws HealthCardException, NotValidePrescriptionException, ConnectException {

        if(visitAgenda.getHealthCardID().equals(null)){
            throw new HealthCardException("Healthcard id does not exist");
        }

        this.CIP = visitAgenda.getHealthCardID();

        if(HNS.getePrescription(CIP).equals(null)){
            throw new NotValidePrescriptionException("Not valid prescription");
        }

        this.prescription = HNS.getePrescription(CIP);

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

    }
    public void selectProduct(int option) throws AnyMedicineSearchException, ConnectException {

    }
    public void enterMedicineGuidelines(String[] instruc) throws AnySelectedMedicineException, IncorrectTakingGuidelinesException {

    }
    public void enterTreatmentEndingDate(Date date) throws IncorrectEndingDateException {

    }
    public void sendePrescription() throws ConnectException,NotValidePrescriptionException, eSignatureException, NotCompletedMedicalPrescription {

    }

    public void printePresc() throws printingException {

    }

    @Override
    public HealthCardID getHealthCardID() throws HealthCardException {
        return this.CIP;
    }

    @Override
    public MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
        return this.prescription;
    }

    @Override
    public List<ProductSpecification> getProductsByKW(String keyWord) throws AnyKeyWordMedicineException, ConnectException {
        return this.productsList;
    }

    @Override
    public ProductSpecification getProductSpecific(int opt) throws AnyMedicineSearchException, ConnectException {
        return this.specification;
    }

    @Override
    public void sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescriptionException, eSignatureException, NotCompletedMedicalPrescription {
        this.prescription = ePresc;
    }
    // Other methods, apart from the input events
}
