import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.*;
import medicalconsultation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MedicalPrescriptionTest {

    private MedicalPrescription medicalPres;
    private HealthCardID hcID;
    private DigitalSignature eSign;
    private ProductID prodID1, prodID2, prodID3;
    private TakingGuideline tgl;
    private MedicalPrescriptionLine mpl1, mpl2, mpl3;
    private HashMap<ProductID, MedicalPrescriptionLine> p_mpl_hash = new HashMap<>();
    private MedicalPrescription mp;

    @BeforeEach
    void setUp() throws NullHealthCardIDException, InvalidHealthCardIDException, NullSignatureException, InvalidProductIDException, NullProductIDException {

        hcID = new HealthCardID("123456789123");
        eSign = new DigitalSignature(new byte[]{ (byte)0xe0, 0x4f, (byte)0xd0, 0x20, (byte)0xea, 0x3a, 0x69, 0x10});
        prodID1 = new ProductID("987654321987");
        prodID2 = new ProductID("147258369456");
        prodID3 = new ProductID("951235748951");
        tgl = new TakingGuideline(dayMoment.AFTERBREAKFAST, 2.34f, "Take 3 times a day", 0.11f, 0.50f, FqUnit.DAY);

        medicalPres = new MedicalPrescription(1111, new Date(2021, 1, 5), new Date(2021, 11, 23), hcID, eSign);

        mpl1 = new MedicalPrescriptionLine(prodID1, tgl);
        mpl2 = new MedicalPrescriptionLine(prodID2, tgl);
        mpl3 = new MedicalPrescriptionLine(prodID3, tgl);



        p_mpl_hash.put(prodID1, mpl1);

    }

    @Test
    void medicalPrescriptionGettersTest(){
        assertEquals(1111 ,medicalPres.getPrescCode());
        assertEquals(new Date(2021, 1, 5), medicalPres.getPrescDate());
        assertEquals(new Date(2021, 11, 23), medicalPres.getEndDate());
        assertEquals(hcID, medicalPres.getHcID());
        assertEquals(eSign, medicalPres.geteSign());
        //assertEquals(lines, medicalPres.getLines());
    }

    @Test
    void medicalPrescriptionSettersTest() throws NullHealthCardIDException, InvalidHealthCardIDException, NullSignatureException {
        medicalPres.setPrescCode(2222);
        medicalPres.setPrescDate(new Date(2022, 4, 5));
        medicalPres.setEndDate(new Date(2023, 2, 18));
        medicalPres.setHcID(new HealthCardID("456789456123"));
        medicalPres.seteSign(new DigitalSignature(new byte[]{ (byte)0x4f, 0x4f, (byte)0xd0, 0x20, (byte)0xea, 0x10, 0x69, 0x3a}));



        assertEquals(2222, medicalPres.getPrescCode());
        assertEquals(new Date(2022, 4, 5), medicalPres.getPrescDate());
        assertEquals(new Date(2023, 2, 18), medicalPres.getEndDate());
        assertEquals(new HealthCardID("456789456123"), medicalPres.getHcID());
        assertEquals(new DigitalSignature(new byte[]{ (byte)0x4f, 0x4f, (byte)0xd0, 0x20, (byte)0xea, 0x10, 0x69, 0x3a}), medicalPres.geteSign());

    }

    @Test
    void addLineTest() throws IncorrectTakingGuidelinesException {
        medicalPres.addLine(prodID1, new String[]{ "AFTERBREAKFAST", "2.34f", "Take 3 times a day", "0.11f", "0.50f", "DAY" });

        assertEquals(prodID1, medicalPres.getPrescriptionLines().get(prodID1).getProduct());
        assertEquals(dayMoment.AFTERBREAKFAST, medicalPres.getPrescriptionLines().get(prodID1).getInstructions().getdMoment());
        assertEquals(2.34f, medicalPres.getPrescriptionLines().get(prodID1).getInstructions().getDuration());
        assertEquals("Take 3 times a day", medicalPres.getPrescriptionLines().get(prodID1).getInstructions().getInstructions());
        assertEquals(0.11f, medicalPres.getPrescriptionLines().get(prodID1).getInstructions().getPosology().getDose());
        assertEquals(0.50f, medicalPres.getPrescriptionLines().get(prodID1).getInstructions().getPosology().getFreq());
        assertEquals(FqUnit.DAY, medicalPres.getPrescriptionLines().get(prodID1).getInstructions().getPosology().getFreqUnit());
    }

    @Test
    void modifyLineTest() {

    }

    @Test
    void removeLineTest() {

    }
}