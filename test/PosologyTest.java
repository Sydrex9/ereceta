import medicalconsultation.FqUnit;
import medicalconsultation.Posology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PosologyTest
{

    private FqUnit fqUnit;
    private Posology posology;

    @BeforeEach
    void setUp()
    {
        fqUnit = FqUnit.WEEK;
        posology = new Posology(5.0f, 1.0f, fqUnit);
    }

    @Test
    void posologyGettersTest()
    {
        assertEquals(5.0f, posology.getDose());
        assertEquals(1.0f, posology.getFreq());
        assertEquals(fqUnit, posology.getFreqUnit());
    }

    @Test
    void posologySettersTest()
    {
        posology.setDose(7.0f);
        posology.setFreq(3.0f);
        posology.setFreqUnit(FqUnit.DAY);

        assertEquals(7.0f, posology.getDose());
        assertEquals(3.0f, posology.getFreq());
        assertEquals(FqUnit.DAY, posology.getFreqUnit());
    }

}