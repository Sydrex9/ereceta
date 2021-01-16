
import data.DigitalSignature;
import exceptions.NullSignatureException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class DataDigitalSignatureTest {

    DigitalSignature digitalSignature;

    @BeforeEach
    void setUp() throws NullSignatureException {

        digitalSignature = new DigitalSignature(new byte[]{(byte) 0xd4,(byte)  0x5e});
    }

    @Test
    public void addTestNullProductIDException() {

        Assertions.assertThrows(NullSignatureException.class, () -> digitalSignature= new DigitalSignature(new byte[]{}));
    }

    @Test
    public void addTestGetDigitalSignatureCode() {

        Assertions.assertArrayEquals(digitalSignature.getDigitalSignatureCode(),new byte[]{(byte) 0xd4,(byte)  0x5e} );

    }



}



