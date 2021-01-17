package services;

import data.HealthCardID;
import exceptions.*;
import medicalconsultation.*;

import java.net.ConnectException;
import java.util.*;

public class HealthNationalServiceDummy implements HealthNationalService{

    private int prescriptionCode;
    private HealthCardID cip;
    private List<ProductSpecification> productsList;
    private HashMap<HealthCardID, MedicalPrescription> prescriptions = new HashMap<>();
    private HashMap<String, List<ProductSpecification>> products = new HashMap<>();

    public HealthNationalServiceDummy (){
    }

    @Override
    public MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
        try {
            if (!prescriptions.containsKey(hcID)) {
                throw new HealthCardException("No current prescriptions");
            }
            if (prescriptions.get(hcID) == null) {
                throw new NotValidePrescriptionException("Prescription not valid");
            }
            cip = hcID;
            return prescriptions.get(hcID);
        } catch (HealthCardException e) {
            throw new HealthCardException("No current prescriptions");
        } catch (NotValidePrescriptionException e) {
            throw new NotValidePrescriptionException("Prescription not valid");
        } catch (Exception e) {
            throw new ConnectException();
        }
    }

    @Override
    public List<ProductSpecification> getProductsByKW(String keyWord) throws AnyKeyWordMedicineException, ConnectException {
        try {
            if (!products.containsKey(keyWord)) {
                throw new AnyKeyWordMedicineException("0 results for this keyword");
            }
            productsList = products.get(keyWord);
            return products.get(keyWord);
        } catch (AnyKeyWordMedicineException e) {
            throw new AnyKeyWordMedicineException("0 results for this keyword");
        } catch (Exception e) {
            throw new ConnectException();
        }
    }

    @Override
    public ProductSpecification getProductSpecific(int opt) throws AnyMedicineSearchException, ConnectException {
        try {
            if (productsList == null || productsList.isEmpty()){
                throw new AnyMedicineSearchException("0 products found");
            }

            if (opt >= productsList.size() || opt < 0){
                throw new AnyMedicineSearchException("0 products found");
            }

            return productsList.get(opt);
        } catch (AnyMedicineSearchException e){
            throw new AnyMedicineSearchException("0 products found");
        } catch (Exception e) {
            throw new ConnectException();
        }
    }

    @Override
    public MedicalPrescription sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescriptionException, eSignatureException, NotCompletedMedicalPrescription {
        try {
            if (ePresc == null || ePresc.getPrescriptionLines().isEmpty()){
                throw new NotValidePrescriptionException("Prescription not valid");
            }
            if (ePresc.geteSign() == null){
                throw new eSignatureException("Incorrect signature");
            }
            if (ePresc.getPrescDate() == null || ePresc.getEndDate() == null){
                throw new NotCompletedMedicalPrescription("Prescription incomplete");
            }
            prescriptionCode++;
            ePresc.setPrescCode(prescriptionCode);
            prescriptions.put(cip, ePresc);
            return prescriptions.get(cip);
        } catch (NotValidePrescriptionException e) {
            throw new NotValidePrescriptionException("Prescription not valid");
        } catch (eSignatureException e) {
            throw new eSignatureException("Incorrect signature");
        } catch (NotCompletedMedicalPrescription e) {
            throw new NotCompletedMedicalPrescription("Prescription incomplete");
        } catch (Exception e) {
            throw new ConnectException();
        }
    }
}