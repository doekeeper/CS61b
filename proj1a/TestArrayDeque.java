import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDeque {

    @Test
    public void test() {
        ArrayDeque L = new ArrayDeque<Integer>();
        L.addFirst(0);
        assertEquals(0, L.removeLast());
        assertTrue(L.isEmpty());
        L.addFirst(4);
        assertEquals(4, L.removeLast());
        assertTrue(L.isEmpty());
    }

}
