import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDeque {

    @Test
    public void test() {
        ArrayDeque L = new ArrayDeque<Integer>();
        L.addFirst(0);
        assertFalse(L.isEmpty());
        assertEquals(0, L.removeFirst());
        assertTrue(L.isEmpty());
        L.addFirst(4);
        L.isEmpty();
        assertEquals(4, L.removeFirst());
        assertTrue(L.isEmpty());
        L.addFirst(8);
        assertEquals(8, L.removeFirst());
        L.addFirst(10);

    }

}
