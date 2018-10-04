import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {

    @Test
    public void isSameNumberTest(){
        assertEquals(true, Flik.isSameNumber(0, 0));
        assertEquals(true, Flik.isSameNumber(127, 127));
        assertEquals(true, Flik.isSameNumber(128, 128));
        assertEquals(true, Flik.isSameNumber(129, 129));


    }
}
