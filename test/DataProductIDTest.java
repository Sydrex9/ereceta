
import data.ProductID;
import exceptions.InvalidProductIDException;
import exceptions.NullProductIDException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataProductIDTest {

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
    public void addTestValid() {

        Assertions.assertEquals(productID.getUPC(), "123456789257");

    }
}
