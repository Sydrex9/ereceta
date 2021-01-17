package services;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.*;
import medicalconsultation.*;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.*;

public class HealthNationalServiceDummy implements HealthNationalService{

    private int prescriptionCode;
    private HealthCardID cip;
    private List<ProductSpecification> productsList;
    private HashMap<HealthCardID, MedicalPrescription> prescriptions = new HashMap<>();
    private HashMap<String, List<ProductSpecification>> products = new HashMap<>();

    public HealthNationalServiceDummy () throws Exception{
        setup();
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

    private void setup() throws Exception{

        HealthCardID id1 = new HealthCardID("123456789147");
        HealthCardID id2 = new HealthCardID("258369789456");
        HealthCardID id3 = new HealthCardID("123369258147");

        DigitalSignature signature1 = new DigitalSignature("PU".getBytes());
        DigitalSignature signature2 = new DigitalSignature("TA".getBytes());

        MedicalPrescription mp1 = new MedicalPrescription(123, new Date(120, 2, 4), new Date(120, 3, 16), id1, signature1);
        MedicalPrescription mp2 = new MedicalPrescription(456, new Date(120, 5, 24), new Date(120, 6, 7), id2, signature2);
        MedicalPrescription mp3 = new MedicalPrescription(1234, new Date(120, 11, 25), new Date(120, 0, 31), id3, signature1);

        ProductID product1 = new ProductID("159753456852");
        ProductID product2 = new ProductID("154446146816");
        ProductID product3 = new ProductID("314526582145");

        String[] instruccion1 = new String[] {"AFTERBREAKFAST", "12", "run", "1", "3", "DAY"};
        String[] instruccion2 = new String[] {"BEFORELUNCH", "13", "hyde", "2", "6", "DAY"};
        String[] instruccion3 = new String[] {"DURINGDINNER", "4", "jump", "17", "9", "WEEK"};

        mp1.addLine(product1, instruccion1);
        mp2.addLine(product2, instruccion2);
        mp3.addLine(product3, instruccion3);

        prescriptions.put(id1, mp1);
        prescriptions.put(id2, mp2);
        prescriptions.put(id3, mp3);

        ProductID haloperidol1 = new ProductID("258654357951");
        ProductID haloperidol2 = new ProductID("825721767152");
        ProductID clozapina1 = new ProductID("132465798195");
        ProductID clozapina2 = new ProductID("253251715215");
        ProductID ziprasidona1 = new ProductID("634174963971");
        ProductID ziprasidona2 = new ProductID("217515711217");

        ProductSpecification h1 = new ProductSpecification(haloperidol1, "600mg", new BigDecimal("12.98"));
        ProductSpecification h2 = new ProductSpecification(haloperidol2, "1g", new BigDecimal("17.93"));
        ProductSpecification c1 = new ProductSpecification(clozapina1, "600mg", new BigDecimal("13.98"));
        ProductSpecification c2 = new ProductSpecification(clozapina2, "1g", new BigDecimal("19.93"));
        ProductSpecification z1 = new ProductSpecification(ziprasidona1, "600mg", new BigDecimal("17.98"));
        ProductSpecification z2 = new ProductSpecification(ziprasidona2, "1g", new BigDecimal("23.97"));

        products.put("haloperidol", new ArrayList<>(Arrays.asList(h1, h2)));
        products.put("clozapina", new ArrayList<>(Arrays.asList(c1, c2)));
        products.put("ziprasidona", new ArrayList<>(Arrays.asList(z1, z2)));
    }
}