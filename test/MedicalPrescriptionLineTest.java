import data.ProductID;
import exceptions.InvalidProductIDException;
import exceptions.NullProductIDException;
import medicalconsultation.FqUnit;
import medicalconsultation.MedicalPrescriptionLine;
import medicalconsultation.TakingGuideline;
import medicalconsultation.dayMoment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicalPrescriptionLineTest {

    private ProductID product;
    private TakingGuideline lineTest;
    private MedicalPrescriptionLine medicalLineTest, medicalLineTest2;


    @BeforeEach
    void setUp() throws InvalidProductIDException, NullProductIDException {

        product=new ProductID("123456789257");
        lineTest= new TakingGuideline(dayMoment.AFTERMEALS,1,"Despues de cada comida",3,1,FqUnit.DAY);



    }

    @Test
    void getProductTest(){

        medicalLineTest=new MedicalPrescriptionLine(product,lineTest);
        assertEquals(product,medicalLineTest.getProduct());

    }

    @Test
    public void getInstructionsTest() {

        medicalLineTest=new MedicalPrescriptionLine(product,lineTest);
        assertEquals(lineTest,medicalLineTest.getInstructions());


    }

    @Test
    public void equalsTest() {

        medicalLineTest=new MedicalPrescriptionLine(product,lineTest);

        medicalLineTest2=new MedicalPrescriptionLine(product,lineTest);

        assertEquals(true, medicalLineTest.equals(medicalLineTest2));


    }

    @Test
    public void setProductTest() {

        medicalLineTest=new MedicalPrescriptionLine(product,lineTest);

        medicalLineTest.setProduct(product);

        assertEquals(product, medicalLineTest.getProduct());


    }

}

