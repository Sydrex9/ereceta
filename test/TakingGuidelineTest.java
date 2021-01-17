import medicalconsultation.FqUnit;
import medicalconsultation.Posology;
import medicalconsultation.TakingGuideline;
import medicalconsultation.dayMoment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TakingGuidelineTest {

    TakingGuideline preTest;
    dayMoment dayMoment,dayMoment2;
    FqUnit fqUnitTest, fqUnitTest2;
    String instructionTest, instructionTest2;
    Posology posologyTest;

    @BeforeEach
    void setUp() {

        dayMoment = dayMoment.AFTERMEALS;
        dayMoment2 = dayMoment.BEFOREBREAKFAST;
        fqUnitTest = FqUnit.DAY;
        fqUnitTest2 = FqUnit.MONTH;
        instructionTest = "Despues de cada comida";
        instructionTest2 = "Antes de cada comida" ;
        posologyTest = new Posology(5,1, fqUnitTest);
        preTest = new TakingGuideline(dayMoment, 2, instructionTest, 5, 1, fqUnitTest);

    }

    @Test
    void GettersTest(){

        assertEquals(preTest.getdMoment(), dayMoment);
        assertEquals(preTest.getDuration(), 2);
        assertEquals(preTest.getInstructions(), instructionTest);
        assertEquals(preTest.getPosology().getDose(), posologyTest.getDose());
        assertEquals(preTest.getPosology().getFreq(), posologyTest.getFreq());
        assertEquals(preTest.getPosology().getFreqUnit(), posologyTest.getFreqUnit());
    }

    @Test
    void SettersTest(){

        preTest.setdMoment(dayMoment2);
        preTest.setDuration(10);
        preTest.setInstructions(instructionTest2);
        preTest.setPosology(new Posology(1,2,fqUnitTest2));

        assertEquals(preTest.getdMoment(),dayMoment2);
        assertEquals(preTest.getDuration(), 10);
        assertEquals(preTest.getInstructions(), instructionTest2);
        assertEquals(preTest.getPosology().getDose(), 1);
        assertEquals(preTest.getPosology().getFreq(), 2);
        assertEquals(preTest.getPosology().getFreqUnit(), fqUnitTest2);
    }

}