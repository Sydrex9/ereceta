

import data.HealthCardID;
import exceptions.InvalidHealthCardIDException;
import exceptions.NullHealthCardIDException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




public class DataHealthCardIDTest {

    HealthCardID healthCardID;

    @BeforeEach
    void setUp() throws NullHealthCardIDException, InvalidHealthCardIDException {

        healthCardID = new HealthCardID(("123456789641"));

    }

    @Test
    public void addTestNullHealthCardIDException() {
        Assertions.assertThrows(NullHealthCardIDException.class, () -> healthCardID = new HealthCardID(("")));
    }

    @Test
    public void addTestInvalidHealthCardIDException() {
        Assertions.assertThrows(InvalidHealthCardIDException.class, () -> healthCardID = new HealthCardID(("112345678964121313")));
    }

    @Test
    public void addTestGetPersonalID() {

        Assertions.assertEquals(healthCardID.getPersonalID(), "123456789641");

    }
}

