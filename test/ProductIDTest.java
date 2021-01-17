
import data.ProductID;
import exceptions.InvalidProductIDException;
import exceptions.NullProductIDException;
import medicalconsultation.MedicalPrescriptionLine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductIDTest {

    ProductID productID;

    @BeforeEach
    void setUp() throws InvalidProductIDException, NullProductIDException {

        productID = new ProductID("123456789257");

    }

    @Test
    public void addTestNullProductIDException() {

        Assertions.assertThrows(NullProductIDException.class, () -> productID = new ProductID(""));
    }

    @Test
    public void addTestInvalidProductIDException() {
        Assertions.assertThrows(InvalidProductIDException.class, () -> productID= new ProductID("A123456789257"));
    }

    @Test
    public void getUPCTestValid() {

        Assertions.assertEquals(productID.getUPC(), "123456789257");

    }

    @Test
    public void equalsTest() throws InvalidProductIDException, NullProductIDException {

        Assertions.assertEquals(true, productID.equals(new ProductID("123456789257")));

    }

    @Test
    public void toStringTest() throws InvalidProductIDException, NullProductIDException {
        //"ProductID{" + "product code='" + UPC + '\'' + '}';
        Assertions.assertEquals("ProductID{product code='123456789257'}", productID.toString());

    }


}
